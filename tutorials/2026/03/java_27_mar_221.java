public class DivideAndConquer {
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("Sorted array: ");
        printArray(arr);
    }

    // Merge two sorted subarrays
    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temp arrays
        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0;
        int j = 0;
        int k = left;

        // Merge two sorted subarrays
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        // Copy remaining elements
        while (i < n1) {
            arr[k++] = L[i++];
        }

        while (j < n2) {
            arr[k++] = R[j++];
        }
    }

    // Divide and Conquer merge sort algorithm
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            // Find the middle element
            int mid = left + (right - left) / 2;

            // Recursively sort subarrays
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Merge two sorted subarrays
            merge(arr, left, mid, right);
        }
    }

    // Print array elements
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}