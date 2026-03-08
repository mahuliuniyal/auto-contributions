import java.util.Arrays;

public class GreedyIntervalScheduling {
    public static void main(String[] args) {
        int[] intervals = {1, 3, 2, 5, 4, 7, 6};
        int[][] result = greedyIntervalScheduling(intervals);
        printResult(result);
    }

    public static int[][] greedyIntervalScheduling(int[] intervals) {
        // Sort the intervals based on their end times
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        // Initialize the result with the first interval
        int[][] result = new int[2][2];
        result[0][0] = intervals[0][0];
        result[0][1] = intervals[0][1];

        // Iterate through the sorted intervals
        for (int i = 1; i < intervals.length; i++) {
            // If the current interval does not overlap with the last selected interval, add it to the result
            if (intervals[i][0] >= result[result.length - 1][1]) {
                result = appendInterval(result, intervals[i]);
            }
        }

        return result;
    }

    public static int[][] appendInterval(int[][] interval, int[] newInterval) {
        // Calculate the start and end times of the new interval
        int start = Math.min(interval[0][0], newInterval[0]);
        int end = Math.max(interval[1][1], newInterval[1]);

        // Return the updated interval
        return new int[][] { new int[] { start, end } };
    }

    public static void printResult(int[][] result) {
        System.out.println("Selected Intervals:");
        System.out.println("(" + result[0][0] + ", " + result[0][1] + ")");
        System.out.println("(" + result[1][0] + ", " + result[1][1] + ")");
    }
}