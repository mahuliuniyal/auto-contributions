// Learning Objective: Build a core Java application that dynamically discovers and loads "plugin" classes
// from external JAR files at runtime using reflection. This tutorial focuses on using URLClassLoader
// to extend the application's classpath and reflection to find and instantiate plugin implementations.

import java.io.File;         // For file system operations (listing directories, creating files)
import java.net.URL;         // Represents a Uniform Resource Locator (used for JAR file paths)
import java.net.URLClassLoader; // The key class loader for loading classes from URLs (JARs)
import java.lang.reflect.Modifier; // Provides static methods to decode class and member modifiers (e.g., isAbstract)
import java.util.ArrayList;  // A mutable list implementation for storing plugins
import java.util.Enumeration; // Used for iterating over entries in a JAR file
import java.util.List;       // The interface for lists
import java.util.jar.JarEntry; // Represents an entry (file or directory) in a JAR file
import java.util.jar.JarFile;  // Represents a JAR file, allowing access to its contents

/**
 * Defines a simple interface that all our "plugin" classes must implement.
 * This provides a common contract, allowing our main application to interact
 * with diverse plugins in a standardized way, leveraging polymorphism.
 *
 * WHY an interface? Because we don't know the exact class names of plugins ahead of time.
 * By defining a common interface, we can treat all loaded plugin objects as `Plugin` types,
 * regardless of their concrete implementation class. This is fundamental for extensible architectures.
 */
interface Plugin {
    String getName(); // Returns a human-readable name for the plugin.
    void execute();   // Performs the plugin's main action or functionality.
}

/**
 * The main application class responsible for discovering, loading, and running plugins.
 * This class contains the core logic for the plugin loading mechanism.
 */
public class PluginLoaderApp {

    // Define the directory where our plugin JARs are expected to reside.
    // In a real-world application, this path might be configurable (e.g., from a properties file).
    private static final String PLUGINS_DIR = "plugins";

    public static void main(String[] args) {
        System.out.println("Starting Plugin Loader Application...");
        System.out.println("Looking for plugins in: " + new File(PLUGINS_DIR).getAbsolutePath());

        // Create the plugins directory if it doesn't exist. This prevents `FileNotFoundException`
        // and provides a clear place for users to put their plugin JARs.
        File pluginDir = new File(PLUGINS_DIR);
        if (!pluginDir.exists()) {
            pluginDir.mkdirs(); // `mkdirs()` creates parent directories too, if necessary.
            System.out.println("Plugin directory '" + PLUGINS_DIR + "' created.");
            System.out.println("Please place your compiled plugin JAR files into this directory.");
            return; // Exit as there are no plugins to load yet.
        }

        // Attempt to load all available plugins from the designated directory.
        List<Plugin> loadedPlugins = loadPlugins(PLUGINS_DIR);

        // Check if any plugins were successfully found and loaded.
        if (loadedPlugins.isEmpty()) {
            System.out.println("\nNo plugins discovered or loaded. Make sure your plugin JARs are correctly placed and structured.");
            // Provide instructions for creating a plugin, as this is a tutorial.
            System.out.println("\n--- How to create a simple plugin JAR: ---");
            System.out.println("1. Create a Java class (e.g., MyPlugin.java) that implements the 'Plugin' interface.");
            System.out.println("   Example: `public class MyPlugin implements Plugin { ... }`");
            System.out.println("2. Compile your plugin: `javac MyPlugin.java` (ensure PluginLoaderApp.java is also compiled for the interface)");
            System.out.println("3. Package it into a JAR: `jar cvf plugins/MyPlugin.jar MyPlugin.class`");
            System.out.println("4. Run this application again: `java PluginLoaderApp`");
        } else {
            System.out.println("\n--- Discovered and Loaded Plugins: ---");
            // Iterate through each loaded plugin and execute its functionality,
            // demonstrating that we can call methods on these dynamically loaded objects.
            for (Plugin plugin : loadedPlugins) {
                System.out.println("Plugin Name: " + plugin.getName());
                System.out.print("Executing Plugin: ");
                plugin.execute(); // Call the `execute` method defined by the `Plugin` interface.
                System.out.println("--------------------");
            }
        }

        System.out.println("\nPlugin Loader Application Finished.");
    }

    /**
     * Discovers and loads plugin classes from JAR files within a specified directory.
     * This method contains the core logic for dynamic plugin loading using `URLClassLoader` and reflection.
     *
     * @param pluginDirPath The path to the directory containing plugin JAR files.
     * @return A list of instantiated Plugin objects that were successfully loaded.
     */
    private static List<Plugin> loadPlugins(String pluginDirPath) {
        List<Plugin> plugins = new ArrayList<>(); // Initialize a list to hold found plugin instances.
        File pluginDir = new File(pluginDirPath); // Create a `File` object for the plugin directory.

        // Basic validation: Check if the provided path is actually a directory.
        if (!pluginDir.isDirectory()) {
            System.err.println("Error: Plugin directory '" + pluginDirPath + "' does not exist or is not a directory.");
            return plugins; // Return an empty list if the directory is invalid.
        }

        // Get all files in the plugin directory that end with ".jar".
        // The lambda `(dir, name) -> name.toLowerCase().endsWith(".jar")` acts as a filter.
        File[] jarFiles = pluginDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".jar"));

        // Handle case where no JAR files are found in the directory.
        if (jarFiles == null || jarFiles.length == 0) {
            System.out.println("No JAR files found in '" + pluginDirPath + "'.");
            return plugins;
        }

        // Iterate through each discovered JAR file.
        for (File jarFile : jarFiles) {
            System.out.println("\nProcessing JAR: " + jarFile.getName());
            try {
                // 1. Create a `URLClassLoader` for the current JAR.
                // A `URLClassLoader` allows us to load classes from specified URLs (like JAR files).
                // We provide `this.getClass().getClassLoader()` (the application's default classloader)
                // as the parent. This ensures that classes within the plugin JAR can "see" classes
                // from our main application, like the `Plugin` interface itself.
                URL jarUrl = jarFile.toURI().toURL(); // Convert the file path to a URL.
                URLClassLoader classLoader = new URLClassLoader(new URL[]{jarUrl}, PluginLoaderApp.class.getClassLoader());

                // 2. Open the JAR file to inspect its contents.
                // Using a try-with-resources statement ensures the `JarFile` is properly closed.
                try (JarFile jar = new JarFile(jarFile)) {
                    Enumeration<JarEntry> entries = jar.entries(); // Get all entries (files and directories) inside the JAR.

                    // Iterate through each entry within the JAR file.
                    while (entries.hasMoreElements()) {
                        JarEntry entry = entries.nextElement();
                        // We are primarily interested in `.class` files that are not directories.
                        if (entry.getName().endsWith(".class") && !entry.isDirectory()) {
                            // Convert the JAR entry path (e.g., "com/example/MyPlugin.class")
                            // into a fully qualified class name (e.g., "com.example.MyPlugin").
                            String className = entry.getName()
                                                    .replace('/', '.') // Replace path separators with package separators
                                                    .substring(0, entry.getName().length() - ".class".length()); // Remove ".class" suffix

                            try {
                                // 3. Dynamically load the class using our `URLClassLoader`.
                                // The `classLoader` knows how to find this class definition within its associated JAR.
                                Class<?> clazz = classLoader.loadClass(className);

                                // 4. Use reflection to check if the loaded class is a concrete implementation of our `Plugin` interface.
                                // `Plugin.class.isAssignableFrom(clazz)`: Checks if `clazz` implements or extends `Plugin`.
                                // `!clazz.isInterface()`: Ensures it's not the interface itself.
                                // `!Modifier.isAbstract(clazz.getModifiers())`: Ensures it's a concrete class that can be instantiated.
                                if (Plugin.class.isAssignableFrom(clazz) &&
                                    !clazz.isInterface() &&
                                    !Modifier.isAbstract(clazz.getModifiers())) {

                                    // 5. Instantiate the plugin class using its default (no-argument) constructor.
                                    // `getDeclaredConstructor().newInstance()` creates a new instance of the class.
                                    // This is the core of dynamic object creation using reflection.
                                    Plugin plugin = (Plugin) clazz.getDeclaredConstructor().newInstance();
                                    plugins.add(plugin); // Add the newly created plugin instance to our list.
                                    System.out.println("  - Discovered and loaded plugin: " + plugin.getName() + " (Class: " + className + ")");
                                }
                            } catch (NoClassDefFoundError | ClassNotFoundException e) {
                                // This catch block handles cases where a class inside the JAR
                                // refers to other classes that couldn't be found (missing dependencies)
                                // or the class itself couldn't be loaded for some reason.
                                System.err.println("  - Could not load class '" + className + "' from " + jarFile.getName() + ": " + e.getMessage());
                            } catch (Exception e) {
                                // Catch other potential issues during instantiation (e.g., no default constructor,
                                // security exceptions, illegal access).
                                System.err.println("  - Error instantiating plugin '" + className + "' from " + jarFile.getName() + ": " + e.getMessage());
                                // For detailed debugging in a tutorial, a stack trace can be useful.
                                // e.printStackTrace();
                            }
                        }
                    }
                } // The `JarFile` is automatically closed here by the try-with-resources statement.

                // IMPORTANT NOTE on `URLClassLoader` lifecycle:
                // In a simple application, leaving the classloader open is usually fine.
                // For long-running applications that load/unload many plugins, closing the classloader
                // (`classLoader.close()`) is crucial to release resources and allow garbage collection
                // of the loaded classes. However, closing a classloader can be complex if plugins
                // share resources or rely on classes loaded by it, so it's omitted here for simplicity
                // but is a key consideration in advanced plugin architectures.

            } catch (Exception e) {
                // Catch any exceptions that occur during JAR file processing (e.g., invalid JAR format).
                System.err.println("Error processing JAR file " + jarFile.getName() + ": " + e.getMessage());
                e.printStackTrace(); // Print the stack trace for full error details in this tutorial.
            }
        }
        return plugins; // Return the list of all found and loaded plugin instances.
    }
}

// --------------------------------------------------------------------------------------------------
// --- Example Usage and Instructions for Creating a Plugin -----------------------------------------
// --------------------------------------------------------------------------------------------------

/*
To fully test this `PluginLoaderApp`, you need to create at least one "plugin" Java class,
compile it, and package it into a JAR file, then place that JAR into the `plugins` directory.

Here's an example plugin and the steps to get it running:

1.  **Create a Plugin Class (e.g., `MyPlugin.java`):**
    Save the following code as `MyPlugin.java` in the *same directory* as `PluginLoaderApp.java`.
    (In a real project, this would be a separate project/module.)

    ```java
    // MyPlugin.java
    import java.time.LocalDateTime;

    // This class MUST implement the 'Plugin' interface defined in PluginLoaderApp.java
    public class MyPlugin implements Plugin {

        // A default (no-argument) constructor is required for simple reflection instantiation.
        public MyPlugin() {
            System.out.println("  MyPlugin: Initialized!");
        }

        @Override
        public String getName() {
            return "My First Dynamic Plugin";
        }

        @Override
        public void execute() {
            System.out.println("  Hello from My First Plugin! Current time: " + LocalDateTime.now());
            System.out.println("  This plugin was loaded dynamically at runtime using URLClassLoader.");
        }
    }
    ```

2.  **Compile the Core App and Plugin:**
    Open a terminal or command prompt in the directory where you saved `PluginLoaderApp.java` and `MyPlugin.java`.
    Compile both files:
    `javac PluginLoaderApp.java MyPlugin.java`

3.  **Create the `plugins` Directory:**
    If the `PluginLoaderApp` hasn't created it already, manually create the `plugins` directory:
    `mkdir plugins`

4.  **Package the Plugin into a JAR:**
    Create a JAR file containing your compiled plugin class (`MyPlugin.class`) and place it in the `plugins` directory:
    `jar cvf plugins/MyPlugin.jar MyPlugin.class`
    (If your plugin was in a package, e.g., `package com.mycompany.plugins;`, you would compile it into `com/mycompany/plugins/MyPlugin.class` and then package it like:
    `jar cvf plugins/MyPlugin.jar -C . com` to include the correct package structure in the JAR.)

5.  **Run the Main Application:**
    Execute the `PluginLoaderApp`:
    `java PluginLoaderApp`

You should see output similar to this, indicating that "My First Dynamic Plugin" was discovered and executed:

```
Starting Plugin Loader Application...
Looking for plugins in: /path/to/your/project/plugins

Processing JAR: MyPlugin.jar
  MyPlugin: Initialized!
  - Discovered and loaded plugin: My First Dynamic Plugin (Class: MyPlugin)

--- Discovered and Loaded Plugins: ---
Plugin Name: My First Dynamic Plugin
Executing Plugin:   Hello from My First Plugin! Current time: 2023-10-27T10:30:45.123
  This plugin was loaded dynamically at runtime using URLClassLoader.
--------------------

Plugin Loader Application Finished.
```

**Experiment further:**
*   Create `AnotherPlugin.java` (similar structure to `MyPlugin.java`).
*   Compile it: `javac AnotherPlugin.java`
*   Package it into a separate JAR: `jar cvf plugins/AnotherPlugin.jar AnotherPlugin.class`
*   Run `java PluginLoaderApp` again. You should see both plugins being discovered and executed!
*/