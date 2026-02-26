// HashMapDemo.java

// Import the necessary classes
import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {

    public static void main(String[] args) {

        // Create a new HashMap
        Map<String, Integer> map = new HashMap<>();

        // Add elements to the map
        // Key-Value pairs are used to store data in the map
        map.put("Apple", 10);
        map.put("Banana", 20);
        map.put("Cherry", 30);

        // Display the map
        System.out.println("Map Contents:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        // Update a value in the map
        map.put("Apple", 40);
        System.out.println("Updated Map Contents:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        // Remove an element from the map
        map.remove("Banana");
        System.out.println("Map Contents After Removal:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        // Check if a key exists in the map
        if (map.containsKey("Cherry")) {
            System.out.println("Key 'Cherry' exists in the map.");
        } else {
            System.out.println("Key 'Cherry' does not exist in the map.");
        }

        // Check if a value exists in the map
        if (map.containsValue(20)) {
            System.out.println("Value 20 exists in the map.");
        } else {
            System.out.println("Value 20 does not exist in the map.");
        }

        // Get the size of the map
        System.out.println("Map Size: " + map.size());

        // Get all the keys in the map
        System.out.println("Map Keys:");
        for (String key : map.keySet()) {
            System.out.println(key);
        }

        // Get all the values in the map
        System.out.println("Map Values:");
        for (Integer value : map.values()) {
            System.out.println(value);
        }
    }
}