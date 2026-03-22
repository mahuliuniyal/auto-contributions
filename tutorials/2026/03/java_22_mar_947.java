public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        System.out.println("Original array: ");
        printArray(arr);

        // Find the index of a target value using binary search
        int target = 23;
        int result = binarySearch(arr, target);
        if (result != -1) {
            System.out.println("Index of " + target + ": " + result);
        } else {
            System.out.println(target + " not found in array.");
        }
    }

    // Function to perform binary search
    public static int binarySearch(int[] arr, int target) {
        // Define the low and high boundaries for the array
        int low = 0;
        int high = arr.length - 1;

        // Continue searching until the boundaries meet or the target is found
        while (low <= high) {
            // Calculate the midpoint index
            int mid = (low + high) / 2;

            // Check if the target value matches the middle element
            if (arr[mid] == target) {
                return mid;
            }

            // If the target value is less than the middle element, update the high boundary
            else if (target < arr[mid]) {
                high = mid - 1;
            }

            // If the target value is greater than the middle element, update the low boundary
            else {
                low = mid + 1;
            }
        }

        // Return -1 to indicate that the target value was not found
        return -1;
    }

    // Helper function to print the array
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
```

Output:
```
Original array: 
2 5 8 12 16 23 38 56 72 91
Index of 23: 5