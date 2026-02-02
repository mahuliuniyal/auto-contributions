// LEARNING OBJECTIVE: This tutorial will guide you through reading data from a CSV file
// and visualizing it as a simple bar chart using Java Swing and the JFreeChart library.
// We will focus on the fundamental steps of data parsing and chart generation.

// Import necessary Java Swing components for the GUI.
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Import JFreeChart classes for creating the bar chart.
// You'll need to add the JFreeChart library to your project's classpath.
// For Maven, add this dependency:
// <dependency>
//     <groupId>org.jfree</groupId>
//     <artifactId>jfreechart</artifactId>
//     <version>1.5.3</version> <!-- Use the latest stable version -->
// </param>
// </dependency>
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

// Define a class to hold our application.
public class CsvBarChartGenerator extends JFrame {

    // This is the main method, the entry point of our Java application.
    public static void main(String[] args) {
        // Create and show the GUI on the Event Dispatch Thread (EDT).
        // This ensures thread-safe GUI updates.
        SwingUtilities.invokeLater(() -> {
            // The path to your CSV file. Replace with your actual file path.
            // For demonstration, let's assume a file named "data.csv" exists.
            // The CSV should have at least two columns: one for categories (e.g., names)
            // and one for numerical values.
            String csvFilePath = "data.csv"; // !!! IMPORTANT: Replace with your CSV file path !!!
            CsvBarChartGenerator app = new CsvBarChartGenerator(csvFilePath);
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // What to do when the window is closed.
            app.setTitle("CSV Data Bar Chart"); // Set the window title.
            app.pack(); // Size the frame based on its components.
            app.setLocationRelativeTo(null); // Center the window on the screen.
            app.setVisible(true); // Make the window visible.
        });
    }

    // Constructor for our application window.
    public CsvBarChartGenerator(String csvFilePath) {
        // Call the superclass (JFrame) constructor.
        super();

        // 1. Read data from CSV file.
        // We'll use a try-catch block to handle potential file reading errors.
        List<String> categories = new ArrayList<>();
        List<Number> values = new ArrayList<>();
        try {
            // Use BufferedReader for efficient reading of text files.
            BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
            String line;
            // Read the CSV line by line.
            while ((line = br.readLine()) != null) {
                // Split the line into columns using the comma as a delimiter.
                String[] columns = line.split(",");
                // Assuming the first column is the category and the second is the value.
                // We're also trimming whitespace and converting to appropriate types.
                if (columns.length >= 2) {
                    categories.add(columns[0].trim()); // Add category (e.g., fruit name)
                    // Try to parse the value as a Double.
                    try {
                        values.add(Double.parseDouble(columns[1].trim())); // Add value (e.g., quantity)
                    } catch (NumberFormatException e) {
                        System.err.println("Warning: Could not parse value '" + columns[1] + "' as a number. Skipping row.");
                        // If parsing fails, we might want to skip this row or handle it differently.
                    }
                }
            }
            br.close(); // Close the file reader when done.
        } catch (IOException e) {
            // If an error occurs during file reading, display an error message.
            JOptionPane.showMessageDialog(this, "Error reading CSV file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            // We'll proceed with an empty dataset if the file can't be read.
        }

        // 2. Create a JFreeChart dataset.
        // DefaultCategoryDataset is suitable for bar charts and line charts.
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Populate the dataset with the data read from the CSV.
        // We're assuming here that the 'categories' and 'values' lists are of the same size.
        for (int i = 0; i < categories.size(); i++) {
            // addValue(value, rowKey, columnKey)
            // Here, rowKey is not strictly necessary for a simple bar chart,
            // but it's a required parameter. We'll use a generic name like "Data".
            // columnKey will be our category from the CSV.
            dataset.addValue(values.get(i), "Data", categories.get(i));
        }

        // 3. Create the bar chart using JFreeChart.
        // ChartFactory.createBarChart(title, categoryAxisLabel, valueAxisLabel, dataset, orientation, legend, tooltips, urls)
        JFreeChart barChart = ChartFactory.createBarChart(
                "Category Values",       // Chart title
                "Category",              // X-axis label (categories)
                "Value",                 // Y-axis label (numerical values)
                dataset,                 // The dataset to visualize
                PlotOrientation.VERTICAL, // Chart orientation (vertical bars)
                false,                   // Include legend (false for simplicity)
                true,                    // Include tooltips
                false                    // Include URLs
        );

        // Optional: Customize the chart appearance (e.g., colors).
        // Get the plot from the chart and then access its renderer.
        CategoryPlot plot = barChart.getCategoryPlot();
        // You can set different renderers or colors here if needed.

        // 4. Create a ChartPanel to display the chart in Swing.
        // ChartPanel is a Swing component that can display a JFreeChart.
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(600, 400)); // Set a preferred size for the panel.

        // Add the ChartPanel to the content pane of our JFrame.
        // This makes the chart appear in the window.
        setContentPane(chartPanel);
    }
}

// EXAMPLE USAGE:
// 1. Create a CSV file named "data.csv" in the same directory as your Java file (or provide the full path).
//    The content of "data.csv" could look like this:
//
//    Apple,50
//    Banana,75
//    Orange,60
//    Grapes,90
//    Mango,45
//
// 2. Ensure you have the JFreeChart library added to your project's classpath.
//
// 3. Compile and run the CsvBarChartGenerator class.
//
// 4. A window will appear displaying a bar chart representing the data from your CSV file.
//    The bars will represent the categories (Apple, Banana, etc.) and their heights will correspond
//    to the values (50, 75, etc.).