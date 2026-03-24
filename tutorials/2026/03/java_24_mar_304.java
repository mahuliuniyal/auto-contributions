// Prefix Sum Code in Java

public class PrefixSum {
    // Function to calculate the prefix sum of an array
    public static int[] prefixSum(int[] arr) {
        int n = arr.length;
        int[] ps = new int[n];

        // Calculate the prefix sum
        ps[0] = arr[0];
        for (int i = 1; i < n; i++) {
            ps[i] = ps[i - 1] + arr[i];
        }

        return ps;
    }

    public static void main(String[] args) {
        // Create an array
        int[] arr = {1, 2, 3, 4, 5};

        // Calculate the prefix sum
        int[] ps = prefixSum(arr);

        // Print the prefix sum
        System.out.println("Prefix Sum:");
        for (int i : ps) {
            System.out.print(i + " ");
        }
    }
}