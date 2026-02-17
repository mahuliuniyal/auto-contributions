// Heap Operations in Java

import java.util.Arrays;

public class HeapOperations {

    // Method to create a min heap from an array
    public static int[] createMinHeap(int[] array) {
        // If the array is empty or contains only one element, it's already a min heap
        if (array.length <= 1) {
            return array;
        }

        // Start from the last non-leaf node and perform heapify operation
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            heapify(array, i);
        }

        return array;
    }

    // Method to heapify a subtree rooted at index i
    public static void heapify(int[] array, int i) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < array.length && array[left] < array[smallest]) {
            smallest = left;
        }

        if (right < array.length && array[right] < array[smallest]) {
            smallest = right;
        }

        if (smallest != i) {
            // Swap elements at index i and smallest
            int temp = array[i];
            array[i] = array[smallest];
            array[smallest] = temp;

            // Recursively heapify the affected subtree
            heapify(array, smallest);
        }
    }

    // Method to extract the minimum element from the min heap
    public static void extractMin(int[] array) {
        if (array.length == 0) {
            return;
        }

        int min = array[0];

        // Replace the root with the last element in the array
        array[0] = array[array.length - 1];
        array[array.length - 1] = min;

        // Reduce the size of the heap by one
        array.length--;

        // Heapify the reduced heap to maintain the min heap property
        heapify(array, 0);
    }

    // Method to insert a new element into the min heap
    public static void insertMin(int[] array, int value) {
        if (array == null) {
            array = new int[1];
        } else {
            array = Arrays.copyOf(array, array.length + 1);
        }

        // Add the new value to the end of the array
        array[array.length - 1] = value;

        // Heapify the new element to maintain the min heap property
        int i = array.length - 1;
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (array[parent] <= array[i]) {
                break;
            }

            // Swap elements at index i and parent
            int temp = array[parent];
            array[parent] = array[i];
            array[i] = temp;

            i = parent;
        }
    }

    public static void main(String[] args) {
        int[] minHeap = {4, 2, 9, 6, 5};
        System.out.println("Original Min Heap: " + Arrays.toString(minHeap));

        // Create a min heap from the array
        minHeap = createMinHeap(minHeap);

        System.out.println("Min Heap after creation: " + Arrays