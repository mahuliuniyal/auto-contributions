import java.util.Scanner;

public class SlidingWindowExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the window: ");
        int windowSize = scanner.nextInt();
        scanner.close();

        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Original Array:");
        printArray(arr);

        slidingWindow(arr, windowSize);
    }

    public static void slidingWindow(int[] arr, int windowSize) {
        // Iterate over the array with the sliding window
        for (int i = 0; i <= arr.length - windowSize; i++) {
            int[] temp = new int[windowSize];
            // Copy the elements of the current window
            System.arraycopy(arr, i, temp, 0, windowSize);
            // Print the current window
            printWindow(temp);
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void printWindow(int[] window) {
        for (int i = 0; i < window.length; i++) {
            System.out.print(window[i] + " ");
        }
        System.out.println();
    }
}