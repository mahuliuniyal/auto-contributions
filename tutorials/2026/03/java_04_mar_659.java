import java.util.Scanner;

public class SegmentTree {
    // Segment tree node class
    static class Node {
        int min;
        int max;

        // Constructor for the node
        Node(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    // Segment tree class
    static class SegmentTree {
        Node[] tree;
        int size;

        // Constructor for the segment tree
        SegmentTree(int[] nums) {
            size = nums.length;
            tree = new Node[4 * size];
            buildTree(nums, 0, 0, size - 1);
        }

        // Function to build the segment tree
        void buildTree(int[] nums, int i, int left, int right) {
            if (left == right) {
                tree[i] = new Node(nums[left], nums[left]);
            } else {
                int mid = left + (right - left) / 2;
                buildTree(nums, 2 * i + 1, left, mid);
                buildTree(nums, 2 * i + 2, mid + 1, right);
                tree[i] = merge(tree[2 * i + 1], tree[2 * i + 2]);
            }
        }

        // Function to merge two nodes
        Node merge(Node left, Node right) {
            return new Node(Math.min(left.min, right.min), Math.max(left.max, right.max));
        }

        // Function to query the minimum value in the range [left, right]
        int query(int left, int right) {
            return query(0, 0, size - 1, left, right);
        }

        // Recursive function to query the minimum value
        int query(int i, int left, int right, int l, int r) {
            if (right < l || r < left) {
                return Integer.MAX_VALUE;
            } else if (l <= left && right <= r) {
                return tree[i].min;
            } else {
                int mid = left + (right - left) / 2;
                return Math.min(query(2 * i + 1, left, mid, l, r), query(2 * i + 2, mid + 1, right, l, r));
            }
        }

        // Function to update the value at index 'i'
        void update(int i, int val) {
            update(0, 0, size - 1, i, val);
        }

        // Recursive function to update the value
        void update(int i, int left, int right, int index, int val) {
            if (index > right || index < left) {
                return;
            } else if (left == right) {
                tree[i].min = val;
                tree[i].max = val;
            } else {
                int mid = left + (right - left) / 2;
                update(2 * i + 1, left, mid, index, val);
                update(2 * i + 2, mid + 1, right, index, val);
                tree[i] = merge(tree[2 * i + 1], tree[2 * i + 2]);
            }
        }
    }

    // Driver code
    public static void main(String[] args) {