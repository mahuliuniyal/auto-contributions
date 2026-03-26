public class TwoPointers {
    public static void main(String[] args) {
        // Create a sorted array of integers from 1 to n where n is the length of the array
        int[] arr = {1, 2, 3, 4, 5};

        // Initialize two pointers, one at the start and one at the end of the array
        int left = 0;
        int right = arr.length - 1;

        // Iterate over the array until the two pointers meet
        while (left < right) {
            // If the elements at the left and right pointers are equal
            if (arr[left] == arr[right]) {
                System.out.println("Elements at " + left + " and " + right + " are equal");
                // Move both pointers towards each other
                left++;
                right--;
            } else if (arr[left] < arr[right]) {
                // If the element at the left pointer is less than the element at the right pointer
                System.out.println("Element at " + left + " is less than element at " + right);
                // Move the left pointer to the right
                left++;
            } else {
                // If the element at the left pointer is greater than the element at the right pointer
                System.out.println("Element at " + left + " is greater than element at " + right);
                // Move the right pointer to the left
                right--;
            }
        }

        if (left == right) {
            System.out.println("Elements at " + left + " and " + right + " are equal");
        } else {
            System.out.println("No pair of elements found that are equal");
        }
    }
}