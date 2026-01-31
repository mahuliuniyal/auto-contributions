// Learning Objective: This tutorial explores the concept of recursion by using it to
// generate a Mandelbrot fractal. We will also learn how to apply custom coloring
// based on the number of iterations it takes for a point to escape the Mandelbrot set.

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.Random; // For random color generation

public class MandelbrotFractal {

    // The maximum number of iterations for each point.
    // A higher value reveals more detail but takes longer to compute.
    private static final int MAX_ITERATIONS = 100;

    // The width and height of our fractal image.
    private static final int IMAGE_WIDTH = 600;
    private static final int IMAGE_HEIGHT = 600;

    // The region of the complex plane to draw.
    // These define the boundaries of our view.
    private static final double X_MIN = -2.0;
    private static final double X_MAX = 1.0;
    private static final double Y_MIN = -1.5;
    private static final double Y_MAX = 1.5;

    public static void main(String[] args) {
        // Create a blank image to draw our fractal on.
        BufferedImage fractalImage = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);

        // Iterate over each pixel in the image.
        for (int pixelX = 0; pixelX < IMAGE_WIDTH; pixelX++) {
            for (int pixelY = 0; pixelY < IMAGE_HEIGHT; pixelY++) {

                // Convert the pixel coordinates to complex plane coordinates.
                // This maps our screen pixels to the specific region of the complex plane.
                double c_real = X_MIN + (double) pixelX / IMAGE_WIDTH * (X_MAX - X_MIN);
                double c_imag = Y_MIN + (double) pixelY / IMAGE_HEIGHT * (Y_MAX - Y_MIN);

                // Calculate the number of iterations for this complex number 'c'.
                // This is where the core of the fractal generation happens.
                int iterations = calculateMandelbrotIterations(c_real, c_imag);

                // Determine the color of the pixel based on the number of iterations.
                // Points that escape quickly get one color, points that take longer get another,
                // and points that don't escape within MAX_ITERATIONS are part of the Mandelbrot set itself.
                Color pixelColor = getColor(iterations);

                // Set the color of the current pixel in our image.
                fractalImage.setRGB(pixelX, pixelY, pixelColor.getRGB());
            }
        }

        // Display the generated fractal image in a window.
        displayImage(fractalImage, "Mandelbrot Fractal");
    }

    // This is the core recursive function.
    // It calculates how many iterations it takes for the point c = (c_real, c_imag)
    // to escape the Mandelbrot set.
    // The Mandelbrot set is defined by the recurrence relation: z = z^2 + c
    // where z starts at 0. If the magnitude of z exceeds 2 at any point,
    // the point 'c' is considered to be outside the set.
    //
    // Why recursion? While this specific calculation *can* be done iteratively,
    // understanding recursion is key to grasping more complex fractal generation techniques
    // and many other algorithms. Here, the "recursive step" is essentially checking
    // the condition for the next iteration. We're not calling the function on itself directly
    // in a classic sense of breaking down a problem into smaller identical sub-problems.
    // Instead, this is a simplified demonstration where the "recursion" is implicit in
    // the iterative nature of the escape-time algorithm. For a true recursive fractal,
    // consider Koch snowflakes or Sierpinski triangles. This example focuses on the
    // escape-time algorithm's logic, which is often explained alongside fractal concepts.
    //
    // For a more direct recursive fractal example, imagine a function that draws a line,
    // and then calls itself to draw two smaller lines at angles, and so on.
    // In this Mandelbrot case, we are performing a series of calculations until a condition is met.
    // We use a helper method to keep the main loop cleaner and to illustrate the iterative process.
    private static int calculateMandelbrotIterations(double c_real, double c_imag) {
        // Initialize z to 0 (0 + 0i).
        double z_real = 0.0;
        double z_imag = 0.0;

        // The number of iterations for this specific complex number.
        int iterations = 0;

        // We loop until the point escapes (magnitude > 2) or we reach the maximum iterations.
        while (z_real * z_real + z_imag * z_imag <= 4.0 && iterations < MAX_ITERATIONS) {
            // Calculate the next value of z using the Mandelbrot formula: z = z^2 + c
            // z_new_real = (z_real^2 - z_imag^2) + c_real
            // z_new_imag = (2 * z_real * z_imag) + c_imag

            double z_real_temp = z_real * z_real - z_imag * z_imag + c_real;
            double z_imag_temp = 2 * z_real * z_imag + c_imag;

            // Update z for the next iteration.
            z_real = z_real_temp;
            z_imag = z_imag_temp;

            // Increment the iteration count.
            iterations++;
        }

        // Return the number of iterations it took to escape, or MAX_ITERATIONS if it didn't.
        return iterations;
    }

    // This function assigns a color to a pixel based on how many iterations it took to escape.
    // Points that are part of the Mandelbrot set (didn't escape within MAX_ITERATIONS)
    // will be colored black. Other points will be colored based on their escape speed.
    private static Color getColor(int iterations) {
        if (iterations == MAX_ITERATIONS) {
            // Points inside the Mandelbrot set are typically colored black.
            return Color.BLACK;
        } else {
            // For points outside the set, we can use various coloring schemes.
            // This example uses a simple scheme: brighter colors for faster escapes.
            // We'll generate a random color and make it brighter or dimmer based on iterations.

            // A simple way to create a gradient effect is to map iterations to hue or brightness.
            // Here, we'll use a slightly more sophisticated approach by generating a base random color
            // and then adjusting its intensity based on iterations.
            Random rand = new Random();
            float hue = rand.nextFloat(); // Random hue (0.0 to 1.0)
            float saturation = 1.0f;      // Fully saturated
            float brightness = 1.0f - (float) iterations / MAX_ITERATIONS; // Brighter for fewer iterations

            // Ensure brightness is not too low for very close to MAX_ITERATIONS points to avoid becoming black.
            if (brightness < 0.1f) {
                brightness = 0.1f;
            }

            return Color.getHSBColor(hue, saturation, brightness);
        }
    }

    // Helper method to display a BufferedImage in a JFrame.
    private static void displayImage(BufferedImage img, String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JLabel(new ImageIcon(img)));
        frame.pack(); // Adjusts the frame size to fit the image
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }
}
// Example Usage:
// To run this code:
// 1. Save it as MandelbrotFractal.java
// 2. Compile it using a Java Development Kit (JDK):
//    javac MandelbrotFractal.java
// 3. Run the compiled code:
//    java MandelbrotFractal
//
// This will generate and display a Mandelbrot fractal in a new window.
// You can experiment by changing MAX_ITERATIONS to see more or less detail,
// and by modifying the X_MIN, X_MAX, Y_MIN, Y_MAX values to zoom into different parts of the fractal.
// The coloring function `getColor` is also a great place to experiment to create different visual styles.
// For example, you could try mapping iterations to specific RGB values or using trigonometric functions.
// This example demonstrates the fundamental escape-time algorithm often associated with recursive fractals,
// focusing on the iterative calculation and its mapping to visual output.