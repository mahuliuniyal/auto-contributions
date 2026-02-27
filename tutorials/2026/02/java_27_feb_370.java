// MergeSort.java

import java.util.Arrays;

public class MergeSort {

    // Merge two subarrays
    public static int[] merge(int[] arr, int low, int mid, int high) {
        int[] left = Arrays.copyOfRange(arr, low, mid + 1);
        int[] right = Arrays.copyOfRange(arr, mid + 1, high + 1);

        int[] merged = new int[high - low + 1];

        int leftIndex = 0;
        int rightIndex = 0;
        int mergeIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                merged[mergeIndex++] = left[leftIndex++];
            } else {
                merged[mergeIndex++] = right[rightIndex++];
            }
        }

        while (leftIndex < left.length) {
            merged[mergeIndex++] = left[leftIndex++];
        }

        while (rightIndex < right.length) {
            merged[mergeIndex++] = right[rightIndex++];
        }

        System.arraycopy(merged, 0, arr, low, merged.length);

        return arr;
    }

    // Sort the array using merge sort
    public static int[] mergeSort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;

            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);

            merge(arr, low, mid, high);
        }

        return arr;
    }

    // Main method
    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 1, 9};
        System.out.println("Original array: " + Arrays.toString(arr));

        int[] sortedArr = mergeSort(arr, 0, arr.length - 1);
        System.out.println("Sorted array: " + Arrays.toString(sortedArr));
    }
}