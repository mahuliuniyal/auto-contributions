// This file teaches how to use Greedy Algorithms in Java.
// A greedy algorithm is a type of algorithm that makes the locally optimal choice at each step
// with the hope of finding a global optimum solution.

import java.util.Arrays;

public class GreedyAlgorithm {
    // The unbounded knapsack problem: given a set of items, each with a weight and value,
    // determine the number of each type of item to include in a collection so that the total
    // value is maximized and the total weight is less than or equal to a given limit.

    public static void main(String[] args) {
        int[] weights = {1, 2, 3, 4, 5};
        int[] values = {10, 20, 30, 40, 50};
        int capacity = 10;

        int[] items = knapsack(weights, values, capacity);
        System.out.println(Arrays.toString(items));
    }

    // The unbounded knapsack problem: given a set of items, each with a weight and value,
    // determine the number of each type of item to include in a collection so that the total
    // value is maximized and the total weight is less than or equal to a given limit.

    public static int[] knapsack(int[] weights, int[] values, int capacity) {
        // Create an array to store the maximum value for each subproblem.
        int[] maxValues = new int[capacity + 1];

        // Create an array to store the items that make up the optimal solution for each subproblem.
        int[] items = new int[capacity + 1];

        // Iterate over all possible capacities from 0 to the maximum capacity.
        for (int i = 1; i <= capacity; i++) {
            // Initialize the maximum value and item for the current capacity to 0.
            maxValues[i] = 0;
            items[i] = -1;

            // Iterate over all items.
            for (int j = 0; j < weights.length; j++) {
                // If the weight of the current item is less than or equal to the current capacity,
                // calculate the value if we include the current item in our collection.
                if (weights[j] <= i) {
                    int currentValue = values[j];

                    // Calculate the value if we exclude the current item from our collection.
                    int excludeValue = maxValues[i - weights[j]];

                    // If including the current item gives us a higher value than excluding it,
                    // update the maximum value and the items that make up the optimal solution for
                    // the current capacity.
                    if (currentValue > excludeValue) {
                        maxValues[i] = currentValue;
                        items[i] = j;

                        // We have found the current item that makes up our optimal solution, so we can break
                        // out of this loop and move on to the next capacity.
                        break;
                    }
                }
            }

            // If no items were included in our collection for the current capacity,
            // then there was no need to include any item in our collection for this subproblem.
            if (items[i] == -1) {
                maxValues[i] = 0;
            }
        }

        // Return the items that make up our optimal solution for the maximum capacity.
        return Arrays.copyOf(items, capacity + 1