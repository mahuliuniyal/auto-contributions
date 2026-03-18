// Binary Search Algorithm in Java

public class BinarySearch {

    public static void main(String[] args) {
        // Create an array of integers for testing
        int[] arr = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};

        // Find the middle index of the array
        System.out.println("Array: " + java.util.Arrays.toString(arr));
        int midIndex = findMiddleIndex(arr.length);

        // Perform binary search on the array
        int result = binarySearch(arr, 0, midIndex, 16);
        System.out.println("Found number 16 at index " + result);
    }

    /**
     * Finds the middle index of an array.
     *
     * @param length The length of the array.
     * @return The middle index of the array.
     */
    public static int findMiddleIndex(int length) {
        // If the array has an odd number of elements, return the integer part of half the length
        if (length % 2 != 0) {
            return length / 2;
        } else {
            // If the array has an even number of elements, return the average of two middle indices
            return length / 2 - 1;
        }
    }

    /**
     * Performs binary search on a sorted array.
     *
     * @param arr   The sorted array to search in.
     * @param left  The starting index of the search range.
     * @param right The ending index of the search range.
     * @param target The number to find in the array.
     * @return The index of the found number, or -1 if not found.
     */
    public static int binarySearch(int[] arr, int left, int right, int target) {
        // While the search range is not empty
        while (left <= right) {
            // Find the middle index of the current search range
            int midIndex = findMiddleIndex(right - left + 1);

            // Calculate the middle value
            int midValue = arr[midIndex];

            // If the target number is equal to the middle value, return the middle index
            if (midValue == target) {
                return midIndex;
            }

            // If the target number is less than the middle value, move the right boundary of the search range
            else if (midValue > target) {
                right = midIndex - 1;
            }

            // If the target number is greater than the middle value, move the left boundary of the search range
            else {
                left = midIndex + 1;
            }
        }

        // Return -1 to indicate that the target number was not found in the array
        return -1;
    }
}