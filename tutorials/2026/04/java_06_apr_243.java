/**
 * This Java program implements the Greedy Interval Scheduling algorithm to schedule meetings.
 */
public class GreedyIntervalScheduling {

    /**
     * This method takes a list of meeting intervals and returns the maximum number of non-overlapping intervals that can be scheduled.
     *
     * @param intervals  A list of meeting intervals where each interval is an array of two integers, representing start time and end time.
     * @return The maximum number of non-overlapping intervals that can be scheduled.
     */
    public static int greedyIntervalScheduling(int[][] intervals) {
        // Sort the intervals based on their end times. This is because we want to schedule the interval with the earliest end time first.
        java.util.Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        // Initialize the count of scheduled intervals and the end time of the last scheduled interval.
        int scheduledCount = 0;
        int lastEndTime = Integer.MIN_VALUE;

        // Iterate over each interval in the sorted list.
        for (int[] interval : intervals) {
            // If the current interval does not overlap with the last scheduled interval, schedule it and update the last end time.
            if (interval[0] >= lastEndTime) {
                scheduledCount++;
                lastEndTime = interval[1];
            }
        }

        // Return the maximum number of non-overlapping intervals that can be scheduled.
        return scheduledCount;
    }

    /**
     * This method takes a list of meeting intervals and returns the maximum number of non-overlapping intervals that can be scheduled,
     * along with the end times of the scheduled intervals.
     *
     * @param intervals  A list of meeting intervals where each interval is an array of two integers, representing start time and end time.
     * @return The maximum number of non-overlapping intervals that can be scheduled, along with the end times of the scheduled intervals.
     */
    public static int[][] greedyIntervalSchedulingWithEndTimes(int[][] intervals) {
        // Sort the intervals based on their end times. This is because we want to schedule the interval with the earliest end time first.
        java.util.Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        // Initialize the count of scheduled intervals and the end times of the scheduled intervals.
        int scheduledCount = 0;
        int[][] scheduledIntervals = new int[scheduledCount][2];

        // Iterate over each interval in the sorted list.
        for (int[] interval : intervals) {
            // If the current interval does not overlap with the last scheduled interval, schedule it and update the scheduled count and end times.
            if (interval[0] >= scheduledIntervals[scheduledCount - 1][1]) {
                scheduledCount++;
                scheduledIntervals[scheduledCount - 1][0] = interval[0];
                scheduledIntervals[scheduledCount - 1][1] = interval[1];
            }
        }

        // Return the maximum number of non-overlapping intervals that can be scheduled, along with their end times.
        return scheduledIntervals;
    }

    public static void main(String[] args) {
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println("Maximum non-overlapping intervals: " + greedyIntervalScheduling(intervals));