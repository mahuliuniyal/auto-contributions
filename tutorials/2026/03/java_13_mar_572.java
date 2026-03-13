// MergeSort.java - A Java implementation of the Merge Sort algorithm.

public class MergeSort {

    // The main method to test the merge sort function.
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original Array:");
        printArray(arr);

        int[] sortedArr = mergeSort(arr);
        System.out.println("\nSorted Array:");
        printArray(sortedArr);
    }

    // The merge sort function takes an array and recursively splits it into two halves until the base case is reached.
    public static int[] mergeSort(int[] arr) {
        if (arr.length <= 1)
            return arr;

        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        // Copy the elements to the left and right arrays.
        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, arr.length - mid);

        // Recursively sort the left and right arrays.
        left = mergeSort(left);
        right = mergeSort(right);

        // Merge the sorted left and right arrays into a single array.
        return merge(left, right);
    }

    // The merge function takes two sorted arrays and merges them into a single sorted array.
    public static int[] merge(int[] left, int[] right) {
        int[] merged = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length)
            if (left[i] <= right[j])
                merged[k++] = left[i++];
            else
                merged[k++] = right[j++];

        // Copy any remaining elements from the left and right arrays.
        while (i < left.length)
            merged[k++] = left[i++];
        while (j < right.length)
            merged[k++] = right[j++];

        return merged;
    }

    // A helper method to print an array.
    public static void printArray(int[] arr) {
        for (int i : arr)
            System.out.print(i + " ");
        System.out.println();
    }
}