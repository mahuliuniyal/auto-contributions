// Merge Sort Algorithm in Java
public class MergeSort {

    // Method to merge two sorted subarrays into one sorted array
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[mid + 1 + j];
        }

        // Merge temp arrays
        int i = 0, j = 0, k = left;
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

        // Copy remaining data
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining data
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main merge sort function
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {

            // Find middle point of array
            int mid = left + (right - left) / 2;

            // Recursively call mergeSort on left and right halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Merge subarrays
            merge(arr, left, mid, right);
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        System.out.println("Original array:");
        printArray(arr);

        // Call merge sort function
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("\nSorted array:");
        printArray(arr);
    }

    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}