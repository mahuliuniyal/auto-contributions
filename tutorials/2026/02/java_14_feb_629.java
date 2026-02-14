public class DynamicProgrammingMemoization {

    public static void main(String[] args) {
        int[] prices = {1, 5, 3, 10, 8};
        System.out.println(findMinPrice(prices));
    }

    /**
     * This function uses dynamic programming with memoization to find the minimum price
     * that can be achieved by buying and selling a stock.
     *
     * @param prices An array of daily stock prices.
     * @return The minimum price that can be achieved.
     */
    public static int findMinPrice(int[] prices) {
        // Create an array to store the minimum price at each day
        int[] minPrices = new int[prices.length];

        // Initialize the first element as the price of the first day
        minPrices[0] = prices[0];

        // Iterate over each day starting from the second day
        for (int i = 1; i < prices.length; i++) {
            // Initialize the minimum price at this day as the current price
            minPrices[i] = prices[i];
            // Check if buying the stock on previous days gives a better result
            for (int j = 0; j < i; j++) {
                // If selling the stock on previous day and buying it today gives a better result
                if (minPrices[j] + prices[i] < minPrices[i]) {
                    // Update the minimum price at this day
                    minPrices[i] = minPrices[j] + prices[i];
                }
            }
        }

        // The minimum price that can be achieved is stored in the last element of minPrices
        return minPrices[prices.length - 1];
    }
}