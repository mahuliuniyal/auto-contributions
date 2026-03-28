import java.util.Scanner;

public class PrefixSum {
    public static void main(String[] args) {
        int[] arr = {10, -20, 30, -40, 50};
        System.out.println("Original array: ");
        for (int i : arr)
            System.out.print(i + " ");
        System.out.println();

        int n = arr.length;
        int[] prefixSum = new int[n];

        // Calculate the prefix sum
        prefixSum[0] = arr[0];
        for (int i = 1; i < n; i++) {
            // For each element in the array, calculate the prefix sum as the sum of elements up to this point
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }

        System.out.println("Prefix Sum: ");
        for (int i : prefixSum)
            System.out.print(i + " ");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the index of element to get its prefix sum: ");
        int index = scanner.nextInt();
        if (index >= 0 && index < n) {
            System.out.println("Prefix Sum at index " + index + ": " + prefixSum[index]);
        } else {
            System.out.println("Invalid index");
        }
    }
}