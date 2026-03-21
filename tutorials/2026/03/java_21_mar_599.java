public class DivideAndConquer {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Original array: ");
        printArray(arr);
        divideAndConquer(arr, 0, arr.length - 1);
        System.out.println("Sorted array: ");
        printArray(arr);
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        // Create temporary arrays for left and right subarrays
        int[] leftArr = new int[mid - left + 1];
        int[] rightArr = new int[right - mid];

        // Copy data to temporary arrays
        System.arraycopy(arr, left, leftArr, 0, mid - left + 1);
        System.arraycopy(arr, mid + 1, rightArr, 0, right - mid);

        int i = 0; // index for left array
        int j = 0; // index for right array
        int k = left; // index for original array

        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        // Copy remaining elements of left and right arrays
        while (i < leftArr.length) {
            arr[k++] = leftArr[i++];
        }
        while (j < rightArr.length) {
            arr[k++] = rightArr[j++];
        }
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            // Recursively sort left and right subarrays
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Merge sorted subarrays
            merge(arr, left, mid, right);
        }
    }

    public static void divideAndConquer(int[] arr) {
        int n = arr.length;
        if (n <= 1) {
            return;
        } else {
            int mid = n / 2;

            // Recursively sort left and right subarrays
            divideAndConquer(arr, 0, mid - 1);
            divideAndConquer(arr, mid, n - 1);

            // Merge sorted subarrays
            mergeSort(arr, 0, n - 1);
        }
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}