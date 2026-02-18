public class MergeSort {

    // Function to merge two sorted subarrays into one sorted array
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        // Copy any remaining elements from the left and right arrays
        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }

        return result;
    }

    // Function to perform merge sort on an array of integers
    public static int[] mergeSort(int[] array) {
        if (array.length <= 1) {
            return array; // Base case: If the length is 1 or less, it's already sorted
        }

        // Find the middle point of the array
        int mid = array.length / 2;

        // Divide the array into two halves
        int[] leftHalf = new int[mid];
        System.arraycopy(array, 0, leftHalf, 0, mid);
        int[] rightHalf = new int[array.length - mid];
        System.arraycopy(array, mid, rightHalf, 0, array.length - mid);

        // Recursively sort the two halves
        leftHalf = mergeSort(leftHalf);
        rightHalf = mergeSort(rightHalf);

        // Merge the sorted halves back together
        return merge(leftHalf, rightHalf);
    }

    public static void main(String[] args) {
        int[] array = {5, 2, 8, 3, 1, 4, 6};
        int[] sortedArray = mergeSort(array);

        // Print the sorted array
        for (int num : sortedArray) {
            System.out.print(num + " ");
        }
    }
}