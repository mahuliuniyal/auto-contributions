public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Original array: ");
        printArray(array);
        
        // Perform binary search
        if (binarySearch(array, 0, array.length - 1, 5)) {
            System.out.println("Element found at index " + findIndex(array, 0, array.length - 1, 5));
        } else {
            System.out.println("Element not found in the array");
        }
    }

    /**
     * Print the elements of an array.
     *
     * @param array The input array
     */
    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    /**
     * Find the index of a target element in a sorted array.
     *
     * @param array The input array
     * @param left  The leftmost index of the current search range
     * @param right The rightmost index of the current search range
     * @param target The target element to find
     * @return True if the element is found, false otherwise
     */
    public static boolean binarySearch(int[] array, int left, int right, int target) {
        // Base case: If the search range is empty, return false
        if (left > right) {
            return false;
        }
        
        // Calculate the middle index of the current search range
        int mid = left + (right - left) / 2;
        
        // Compare the middle element with the target element
        if (array[mid] == target) {
            return true; // Element found, return true
        } else if (array[mid] < target) {
            // If the middle element is less than the target, search in the right half
            return binarySearch(array, mid + 1, right, target);
        } else {
            // If the middle element is greater than the target, search in the left half
            return binarySearch(array, left, mid - 1, target);
        }
    }

    /**
     * Find the index of a target element in a sorted array.
     *
     * @param array The input array
     * @param left  The leftmost index of the current search range
     * @param right The rightmost index of the current search range
     * @param target The target element to find
     * @return The index of the target element if found, -1 otherwise
     */
    public static int findIndex(int[] array, int left, int right, int target) {
        // Base case: If the search range is empty, return -1
        if (left > right) {
            return -1;
        }
        
        // Calculate the middle index of the current search range
        int mid = left + (right - left) / 2;
        
        // Compare the middle element with the target element
        if (array[mid] == target) {
            return mid; // Element found, return its index
        } else if (array