import java.util.Arrays;

public class MergeSort {

    // Method to merge two sorted subarrays into one sorted subarray
    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        // Initialize indices for merging
        int i = 0;
        int j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements from L[], if there are any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining element from R[], if there are any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Method to implement merge sort algorithm
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {

            // Find the middle index of the subarray
            int mid = left + (right - left) / 2;

            // Recursively call mergeSort on the left and right halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Merge the sorted subarrays
            merge(arr, left, mid, right);
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 7, 1, 3};
        System.out.println("Original array: " + Arrays.toString(arr));

        // Call the mergeSort method to sort the array
        mergeSort(arr, 0, arr.length - 1);

        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}