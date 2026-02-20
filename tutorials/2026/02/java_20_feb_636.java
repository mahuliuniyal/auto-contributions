import java.util.*;

public class MonotonicStack {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println("Original array: ");
        printArray(nums);

        int[] sorted = monotonicStack(nums);
        System.out.println("Sorted array using monotonic stack: ");
        printArray(sorted);
    }

    public static int[] monotonicStack(int[] nums) {
        int n = nums.length;
        int[] stack = new int[n];
        int top = -1;

        for (int i = 0; i < n; i++) {
            // While the stack is not empty and the current number is greater than the top of the stack
            while (top >= 0 && nums[i] > nums[stack[top]]) {
                // Pop the top element from the stack
                top--;
            }

            // If the stack is empty or the current number is smaller than the top of the stack
            if (top < 0 || nums[i] < nums[stack[top]]) {
                // Push the current index to the stack
                stack[++top] = i;
            }
        }

        int[] result = new int[n];
        // Pop elements from the stack and store them in the result array
        for (int i = 0; i < n; i++) {
            result[stack[top--]] = nums[stack[top]];
        }
        return result;
    }

    public static void printArray(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}