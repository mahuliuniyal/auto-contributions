// SegmentTree.java

public class SegmentTree {

    // Node class to represent the node in the segment tree
    static class Node {
        int min;
        int max;

        public Node(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    // Segment tree constructor with size 'n' and default values (0, n)
    public SegmentTree(int n) {
        this.n = n;
        this.tree = new Node[4 * n + 1];
        buildSegmentTree(1, 0, n - 1);
    }

    // Function to build the segment tree
    private void buildSegmentTree(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(start, end);
            return;
        }
        int mid = start + (end - start) / 2;
        buildSegmentTree(2 * node, start, mid);
        buildSegmentTree(2 * node + 1, mid + 1, end);

        // Combine the results from left and right subtrees
        tree[node] = combine(tree[2 * node], tree[2 * node + 1]);
    }

    // Function to update a value in the segment tree at index 'idx'
    public void update(int idx, int value) {
        update(1, 0, n - 1, idx, value);
    }

    // Recursive function to update a value in the segment tree
    private void update(int node, int start, int end, int idx, int value) {
        if (start == end && start == idx) {
            tree[node] = new Node(value, value);
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid)
            update(2 * node, start, mid, idx, value);
        else
            update(2 * node + 1, mid + 1, end, idx, value);

        // Combine the results from left and right subtrees
        tree[node] = combine(tree[2 * node], tree[2 * node + 1]);
    }

    // Function to query the range [start, end]
    public int query(int start, int end) {
        return query(1, 0, n - 1, start, end);
    }

    // Recursive function to query a range in the segment tree
    private int query(int node, int start, int end, int qStart, int qEnd) {
        if (qStart <= start && qEnd >= end)
            return tree[node].min;
        if (qStart > end || qEnd < start)
            throw new IllegalArgumentException("Invalid range");

        int mid = start + (end - start) / 2;

        // Query the left and right subtrees
        int leftMin = query(2 * node, start, mid, qStart, qEnd);
        int rightMin = query(2 * node + 1, mid + 1, end, qStart, qEnd);

        return combine(leftMin, rightMin);
    }

    // Function to get the combined result of two nodes
    private Node combine(Node left, Node right) {
        return new Node(Math.min(left.min, right.min),