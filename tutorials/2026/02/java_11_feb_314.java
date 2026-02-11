// DynamicProgrammingMemoization.java

public class DynamicProgrammingMemoization {

    /**
     * This method uses dynamic programming with memoization to find the minimum number of coins needed for a given amount.
     *
     * @param coins   An array of available coin denominations
     * @param amount  The target amount
     * @return The minimum number of coins needed
     */
    public static int minCoins(int[] coins, int amount) {
        // Create an array to store the minimum number of coins needed for each amount from 0 to the target amount
        int[] dp = new int[amount + 1];
        
        // Initialize all values in the array to infinity except for the first one (dp[0] = 0)
        for (int i = 0; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;

        // Iterate through each coin denomination
        for (int coin : coins) {
            // Iterate from the coin value to the target amount
            for (int i = coin; i <= amount; i++) {
                // Update the minimum number of coins needed if using the current coin results in a smaller count
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        // Return the minimum number of coins needed for the target amount, or -1 if no solution exists
        return (dp[amount] == Integer.MAX_VALUE) ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println("Minimum number of coins needed: " + minCoins(coins, amount));
    }
}