// Divide and Conquer in Java

public class DivideAndConquer {
    // Function to find the maximum element in an array
    public static int findMax(int[] array) {
        // Base case: If the array has one element, return it
        if (array.length == 1) {
            return array[0];
        }

        // Divide the array into two halves
        int mid = array.length / 2;
        int[] leftHalf = new int[mid];
        int[] rightHalf = new int[array.length - mid];

        System.arraycopy(array, 0, leftHalf, 0, mid);
        System.arraycopy(array, mid, rightHalf, 0, array.length - mid);

        // Recursively find the maximum element in each half
        int maxLeft = findMax(leftHalf);
        int maxRight = findMax(rightHalf);

        // Combine the results
        return Math.max(maxLeft, maxRight);
    }

    // Function to find the minimum element in an array
    public static int findMin(int[] array) {
        // Base case: If the array has one element, return it
        if (array.length == 1) {
            return array[0];
        }

        // Divide the array into two halves
        int mid = array.length / 2;
        int[] leftHalf = new int[mid];
        int[] rightHalf = new int[array.length - mid];

        System.arraycopy(array, 0, leftHalf, 0, mid);
        System.arraycopy(array, mid, rightHalf, 0, array.length - mid);

        // Recursively find the minimum element in each half
        int minLeft = findMin(leftHalf);
        int minRight = findMin(rightHalf);

        // Combine the results
        return Math.min(minLeft, minRight);
    }

    // Function to find the sum of all elements in an array
    public static int findSum(int[] array) {
        // Base case: If the array has one element, return it
        if (array.length == 1) {
            return array[0];
        }

        // Divide the array into two halves
        int mid = array.length / 2;
        int[] leftHalf = new int[mid];
        int[] rightHalf = new int[array.length - mid];

        System.arraycopy(array, 0, leftHalf, 0, mid);
        System.arraycopy(array, mid, rightHalf, 0, array.length - mid);

        // Recursively find the sum of each half
        int sumLeft = findSum(leftHalf);
        int sumRight = findSum(rightHalf);

        // Combine the results
        return sumLeft + sumRight;
    }

    // Main function to test the divide and conquer algorithms
    public static void main(String[] args) {
        int[] array = {12, 34, 56, 78, 90};
        System.out.println("Maximum element: " + findMax(array));
        System.out.println("Minimum element: " + findMin(array));
        System.out.println("Sum of all elements: " + findSum(array));
    }
}