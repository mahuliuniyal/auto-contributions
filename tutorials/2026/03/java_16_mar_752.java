// Segment Tree Implementation in Java
public class SegmentTree {

    // Node structure for the segment tree
    static class Node {
        int minVal;
        int maxVal;

        public Node(int minVal, int maxVal) {
            this.minVal = minVal;
            this.maxVal = maxVal;
        }
    }

    // Constructor to initialize the segment tree
    public SegmentTree(int[] arr) {
        n = arr.length;
        buildTree(arr);
    }

    // Function to update a node in the segment tree
    private void updateNode(Node node, int left, int right, int index, int val) {
        if (left == right) {
            node.minVal = Math.min(node.minVal, val);
            node.maxVal = Math.max(node.maxVal, val);
            return;
        }

        int mid = (left + right) / 2;

        // Recursively update the left and right subtrees
        if (index <= mid)
            updateNode(node.left, left, mid, index, val);
        else
            updateNode(node.right, mid + 1, right, index, val);

        // Update the current node with the values from its children
        node.minVal = Math.min(node.left.minVal, node.right.minVal);
        node.maxVal = Math.max(node.left.maxVal, node.right.maxVal);
    }

    // Function to query a range in the segment tree
    private int queryRange(Node node, int left, int right, int l, int r) {
        if (l > r)
            return Integer.MAX_VALUE;

        if (left == l && right == r)
            return node.minVal;

        int mid = (left + right) / 2;
        int minValLeft = queryRange(node.left, left, mid, l, Math.min(r, mid));
        int maxValRight = queryRange(node.right, mid + 1, right, Math.max(l, mid + 1), r);

        // Return the minimum value in the range
        return Math.min(minValLeft, maxValRight);
    }

    // Function to build the segment tree recursively
    private void buildTree(int[] arr) {
        for (int i = n / 2; i >= 0; --i) {
            if (n % 2 == 1 && i == 0)
                node[i] = new Node(arr[i], arr[i]);
            else if ((n & 1) != 0 || i < n / 2)
                node[i] = new Node(
                        Math.min(node[2 * i].minVal, node[2 * i + 1].minVal),
                        Math.max(node[2 * i].maxVal, node[2 * i + 1].maxVal));
        }
    }

    // Array to store the nodes of the segment tree
    private Node[] node;

    // Size of the input array
    private int n;

    public static void main(String[] args) {
        SegmentTree segmentTree = new SegmentTree(new int[]{5, 3, 8, 2, 9});
        System.out.println("Minimum value in range [0, 4]: " + segmentTree.queryRange(segmentTree.node[1], 0, 4, 0, 4));
        System.out.println