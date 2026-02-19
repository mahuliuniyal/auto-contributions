// UnionFind.java: This program teaches the union-find algorithm, a data structure used to manage a set of elements partitioned into a number of disjoint groups.

public class UnionFind {

    // Data structure to store the parent of each element
    private int[] parent;

    // Data structure to store the rank of each element
    private int[] rank;

    // Number of elements in the union-find data structure
    private int numElements;

    // Constructor to initialize the union-find data structure with a given number of elements
    public UnionFind(int numElements) {
        this.numElements = numElements;
        parent = new int[numElements];
        rank = new int[numElements];

        // Initialize each element as its own parent and set its rank to 0
        for (int i = 0; i < numElements; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    // Method to find the root of a given element
    private int find(int element) {
        // If the element is not its own parent, find its root and update its parent if necessary
        if (parent[element] != element) {
            parent[element] = find(parent[element]);
        }
        return parent[element];
    }

    // Method to union two elements by making one their parent
    public void union(int element1, int element2) {
        // Find the roots of both elements
        int root1 = find(element1);
        int root2 = find(element2);

        // If the roots are different, merge the groups by making the parent with lower rank the parent of the other
        if (root1 != root2) {
            if (rank[root1] < rank[root2]) {
                parent[root1] = root2;
            } else if (rank[root1] > rank[root2]) {
                parent[root2] = root1;
            } else { // If the ranks are equal, make one element's rank 0 and the other its parent
                parent[root2] = root1;
                rank[root1]++;
            }
        }
    }

    // Method to check if two elements are in the same group (union)
    public boolean union(int element1, int element2) {
        return find(element1) == find(element2);
    }

    // Example usage
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);

        System.out.println("Initial elements: " + uf.parent[0] + ", " + uf.parent[1]); // 0, 1

        uf.union(2, 6); // Merge groups containing elements 2 and 6
        System.out.println("After unioning 2 and 6: " + uf.parent[2] + ", " + uf.parent[6]); // 0, 0

        System.out.println("Is 2 in the same group as 6? " + uf.union(2, 6)); // true
        System.out.println("Is 1 in the same group as 9? " + uf.union(1, 9)); // false

        System.out.println("After unioning 1 and 9: " + uf.parent[1] + ", " + uf.parent[9]); // 0, 0
    }
}