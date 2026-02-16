public class DivideAndConquer {

    // Step 1: Define the function to find the maximum of two numbers using division and conquer approach.
    public static int max(int a, int b) {
        // Base case: if b is zero, return a (since we cannot divide by zero).
        if (b == 0)
            return a;
        
        // Recursive call: add a to itself divided by 2.
        else
            return max(a + b / 2 * 2, b);
    }

    public static void main(String[] args) {
        int num1 = 50;
        int num2 = 30;
        System.out.println("Maximum of " + num1 + " and " + num2 + " is " + max(num1, num2));

        // Step 2: Define the function to find the maximum element in an array using divide and conquer approach.
        public static void printMax(int[] arr) {
            if (arr.length == 0)
                return;
            
            int mid = arr.length / 2;
            int[] leftHalf = new int[mid];
            int[] rightHalf = new int[arr.length - mid];

            System.arraycopy(arr, 0, leftHalf, 0, mid);
            System.arraycopy(arr, mid, rightHalf, 0, arr.length - mid);

            printMax(leftHalf); // Divide the array into two halves
            printMax(rightHalf); // Recursively find maximum in each half

            int maxLeft = leftHalf[0]; // Initialize maxLeft as first element of leftHalf
            for (int i = 1; i < leftHalf.length; i++)
                if (leftHalf[i] > maxLeft)
                    maxLeft = leftHalf[i];

            int maxRight = rightHalf[0];
            for (int i = 1; i < rightHalf.length; i++) {
                if (rightHalf[i] > maxRight)
                    maxRight = rightHalf[i];
            }

            System.out.print("Maximum element in the array is: ");
            if (maxLeft >= maxRight) 
                System.out.println(maxLeft);
            else
                System.out.println(maxRight); // Print maximum of two halves.
        }
        
        printMax(new int[] {12, 34, 54, 2, 3, 5, 7, 23});
    }
}