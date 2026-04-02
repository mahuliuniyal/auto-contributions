import java.util.*;

public class HeapOperations {

    // Create an empty max heap
    public static void createMaxHeap() {
        System.out.println("Creating a max heap:");
        int[] array = {12, 11, 13, 5, 6, 7};
        heapify(array);
        printHeap(array);
    }

    // Function to swap two elements in the heap
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Function to heapify a subtree rooted at index i
    public static void heapify(int[] array, int n, int i) {
        int largest = i;  // Initialize the root as the largest element
        int left = 2 * i + 1;  // Calculate the left child index
        int right = 2 * i + 2;  // Calculate the right child index

        if (left < n && array[left] > array[largest])
            largest = left;

        if (right < n && array[right] > array[largest])
            largest = right;

        if (largest != i) {
            swap(array, i, largest);
            heapify(array, n, largest);  // Recursively heapify the affected subtree
        }
    }

    // Function to build a max heap from an unsorted array
    public static void buildMaxHeap(int[] array) {
        int n = array.length;
        for (int i = n / 2 - 1; i >= 0; i--) {  // Start from the last non-leaf node and go up to the root
            heapify(array, n, i);
        }
    }

    // Function to print a max heap
    public static void printHeap(int[] array) {
        System.out.println("Max Heap:");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    // Function to extract the maximum element from a max heap
    public static void extractMax(int[] array) {
        if (array.length > 0) {
            int max = array[0];  // Store the maximum element
            array[0] = array[array.length - 1];  // Replace the root with the last element
            array.pop();
            heapify(array, array.length - 1, 0);  // Heapify the reduced heap
            System.out.println("Extracted Max: " + max);
        }
    }

    public static void main(String[] args) {
        createMaxHeap();
        buildMaxHeap(new int[]{12, 11, 13, 5, 6, 7});
        printHeap(new int[]{12, 11, 13, 5, 6, 7});

        // Extract the maximum element
        extractMax(new int[]{12, 11, 13, 5, 6, 7});
        extractMax(new int[]{4, 2, 9, 6, 5});
    }
}