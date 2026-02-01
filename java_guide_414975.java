// Learning Objective: This tutorial demonstrates how to programmatically generate
// and visualize the Mandelbrot fractal using Java's Swing and AWT graphics capabilities.
// We will learn about:
// 1. The mathematical definition of the Mandelbrot set.
// 2. Iterating complex numbers to determine if they belong to the set.
// 3. Mapping fractal iteration counts to colors for visualization.
// 4. Using Java's Graphics2D API to draw pixels on a window.

import javax.swing.JFrame; // For creating the main window of our application.
import javax.swing.JPanel; // A lightweight container that can display graphics.
import java.awt.Graphics; // The base class for all graphics contexts in Java.
import java.awt.Graphics2D; // Provides more sophisticated 2D graphics capabilities.
import java.awt.Color; // For representing colors.
import java.awt.image.BufferedImage; // An image with an accessible buffer of image data.

public class MandelbrotGenerator extends JPanel {

    // These constants define the region of the complex plane we're interested in.
    // The Mandelbrot set is typically viewed within the range [-2.0, 1.0] for the real part
    // and [-1.5, 1.5] for the imaginary part.
    private static final double RE_START = -2.0;
    private static final double RE_END = 1.0;
    private static final double IM_START = -1.5;
    private static final double IM_END = 1.5;

    // The maximum number of iterations to perform for each point.
    // A higher value means more detail but takes longer to compute.
    // This value also influences the number of distinct colors we can use.
    private static final int MAX_ITERATIONS = 256;

    // The image to store the computed fractal data.
    // We use BufferedImage for efficient pixel manipulation.
    private BufferedImage mandelbrotImage;

    // Constructor for our MandelbrotGenerator panel.
    public MandelbrotGenerator() {
        // Initialize the image. The width and height will be determined by the panel's size.
        // We use TYPE_INT_RGB as it's a common and efficient image type.
        mandelbrotImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        // Call a method to actually compute and draw the fractal.
        computeMandelbrot();
    }

    // This method is called automatically by Swing when the panel needs to be repainted.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Important: Call the superclass method first.

        // Cast the Graphics object to Graphics2D for advanced features.
        Graphics2D g2d = (Graphics2D) g;

        // We only need to compute the fractal once, or when the panel size changes.
        // For simplicity in this tutorial, we compute it in the constructor.
        // In a real application, you might recompute it if getWidth() or getHeight() changes.

        // Draw the pre-computed fractal image onto the panel.
        g2d.drawImage(mandelbrotImage, 0, 0, this);
    }

    // This is the core method that computes the Mandelbrot fractal.
    private void computeMandelbrot() {
        // Ensure the image has been initialized with the correct dimensions.
        // If the panel size changes, we might need to re-initialize.
        if (getWidth() == 0 || getHeight() == 0) {
            return; // Cannot draw if dimensions are zero.
        }
        mandelbrotImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

        // Loop through each pixel of the image.
        // Each pixel will correspond to a point in the complex plane.
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {

                // Map the pixel coordinates (x, y) to a point (c_re, c_im) in the complex plane.
                // We normalize the pixel coordinates to fit within our defined complex plane region.
                double c_re = RE_START + (double) x / getWidth() * (RE_END - RE_START);
                double c_im = IM_START + (double) y / getHeight() * (IM_END - IM_START);

                // Initialize the complex number z = 0.
                double z_re = 0;
                double z_im = 0;

                // Keep track of the number of iterations.
                int i = 0;

                // The Mandelbrot fractal is defined by the recurrence relation: z_{n+1} = z_n^2 + c
                // where z_0 = 0, and c is a complex number.
                // A complex number c belongs to the Mandelbrot set if the magnitude of z_n
                // remains bounded as n approaches infinity.
                // We approximate this by checking if |z_n| exceeds a certain threshold (e.g., 2).
                // If it exceeds the threshold, the point is outside the set.
                // We also stop if we reach MAX_ITERATIONS, assuming it's inside the set or very slow to diverge.

                // The condition |z| > 2 is a standard mathematical property used to determine divergence.
                // Squaring this, we get |z|^2 > 4.
                // For complex numbers, |z|^2 = z_re^2 + z_im^2.
                while (z_re * z_re + z_im * z_im <= 4.0 && i < MAX_ITERATIONS) {
                    // Calculate z^2. For complex numbers (a + bi)^2 = (a^2 - b^2) + (2ab)i.
                    double z_re_temp = z_re * z_re - z_im * z_im; // Real part of z^2
                    double z_im_temp = 2 * z_re * z_im;         // Imaginary part of z^2

                    // Update z: z = z^2 + c
                    z_re = z_re_temp + c_re; // New real part of z
                    z_im = z_im_temp + c_im; // New imaginary part of z

                    i++; // Increment the iteration count.
                }

                // Determine the color based on the number of iterations.
                Color pixelColor;
                if (i == MAX_ITERATIONS) {
                    // If we reached MAX_ITERATIONS, the point is considered to be in the set.
                    // We color it black.
                    pixelColor = Color.BLACK;
                } else {
                    // If the loop broke early, the point is outside the set.
                    // We color it based on how quickly it diverged (number of iterations).
                    // This mapping creates the intricate patterns outside the set.
                    // We use a simple hue-based coloring for demonstration.
                    // The modulo operator (%) helps us cycle through colors if MAX_ITERATIONS is large.
                    float hue = (float) i / MAX_ITERATIONS; // Normalize iterations to a 0-1 range.
                    float saturation = 1.0f;
                    float brightness = 1.0f;
                    pixelColor = Color.getHSBColor(hue, saturation, brightness);
                }

                // Set the color of the current pixel in our BufferedImage.
                mandelbrotImage.setRGB(x, y, pixelColor.getRGB());
            }
        }
    }

    // Main method to create and display the Mandelbrot fractal window.
    public static void main(String[] args) {
        // Create a new JFrame, which is the main window.
        JFrame frame = new JFrame("Mandelbrot Fractal");

        // Set the default close operation. When the user clicks the close button,
        // the application will exit.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create an instance of our MandelbrotGenerator panel.
        MandelbrotGenerator mandelbrotPanel = new MandelbrotGenerator();

        // Set the preferred size of the panel. This suggests a size, but the frame can resize it.
        // We can make it dynamically sized by omitting this, but it's good for a starting point.
        mandelbrotPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        // Add the MandelbrotGenerator panel to the frame.
        frame.add(mandelbrotPanel);

        // Pack the frame. This sizes the frame so that all its contents are at their
        // preferred sizes.
        frame.pack();

        // Make the frame visible. By default, windows are not visible when created.
        frame.setVisible(true);

        // Set the frame's location to be centered on the screen for better user experience.
        frame.setLocationRelativeTo(null);
    }
}