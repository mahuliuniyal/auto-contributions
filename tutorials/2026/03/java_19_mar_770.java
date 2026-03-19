// Segment Tree in Java

public class SegmentTree {
    // Node class representing a node in the segment tree
    static class Node {
        int min;
        int max;

        public Node(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    // Constructor for the Segment Tree
    public SegmentTree(int[] nums) {
        n = nums.length;
        tree = new Node[n * 4];
        buildSegmentTree(nums, 0, n - 1, 1);
    }

    // Function to build the segment tree
    private void buildSegmentTree(int[] nums, int start, int end, int index) {
        if (start == end) {
            tree[index] = new Node(nums[start], nums[start]);
        } else {
            int mid = (start + end) / 2;
            buildSegmentTree(nums, start, mid, index * 2);
            buildSegmentTree(nums, mid + 1, end, index * 2 + 1);

            // Update the node with the minimum and maximum values from its children
            tree[index] = new Node(Math.min(tree[index * 2].min, tree[index * 2 + 1].min),
                    Math.max(tree[index * 2].max, tree[index * 2 + 1].max));
        }
    }

    // Function to query the segment tree for a range
    public int query(int left, int right) {
        return querySegmentTree(0, n - 1, left, right, 1);
    }

    // Function to query the segment tree recursively
    private int querySegmentTree(int start, int end, int queryLeft, int queryRight, int index) {
        if (queryLeft > queryRight)
            return Integer.MAX_VALUE; // or any other value representing infinity

        if (start == queryLeft && end == queryRight)
            return tree[index].min;

        int mid = (start + end) / 2;
        if (queryRight <= mid)
            return querySegmentTree(start, mid, queryLeft, queryRight, index * 2);
        else if (queryLeft > mid)
            return querySegmentTree(mid + 1, end, queryLeft, queryRight, index * 2 + 1);
        else {
            int leftMin = Math.min(querySegmentTree(start, mid, queryLeft, mid, index * 2),
                    querySegmentTree(mid + 1, end, mid + 1, queryRight, index * 2 + 1));
            int rightMax = Math.max(querySegmentTree(start, mid, mid + 1, queryRight, index * 2),
                    querySegmentTree(mid + 1, end, queryLeft, queryRight, index * 2 + 1));

            return new Node(leftMin, rightMax).min;
        }
    }

    // Function to update the segment tree at a given index
    public void update(int idx, int val) {
        updateSegmentTree(0, n - 1, idx, val, 1);
    }

    // Function to update the segment tree recursively
    private void updateSegmentTree(int start, int end, int idx, int val, int index) {
        if (start == end && start == idx)
            tree[index