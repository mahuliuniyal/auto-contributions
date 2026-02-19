public class UnionFind {
    // This program teaches the Union-Find algorithm, a data structure used in graph theory and computer science.
    // The Union-Find algorithm is useful for solving problems that involve grouping elements into sets,
    // such as finding connected components in a graph or testing whether two elements are in the same set.

    private int[] parent; // Array to store the parent of each element
    private int[] rank;   // Array to store the rank of each element

    public UnionFind(int size) {
        // Initialize the parent and rank arrays
        parent = new int[size];
        rank = new int[size];

        // Initialize all elements as their own set (rank 0)
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public void union(int x, int y) {
        // Find the root of each element's set
        int rootX = find(x);
        int rootY = find(y);

        // If the sets are not already connected, merge them
        if (rootX != rootY) {
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                // If the ranks are equal, make one set the leader of the other
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    public int find(int x) {
        // Path compression: update the parent of each element in the path to its root
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean connected(int x, int y) {
        // Check if two elements are in the same set
        return find(x) == find(y);
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(6);

        // Test cases
        System.out.println("Test case 1: union(0, 1)");
        uf.union(0, 1); // Expected output: connected(0, 1)
        System.out.println(uf.connected(0, 1)); // Expected output: true

        System.out.println("Test case 2: union(2, 3)");
        uf.union(2, 3); // Expected output: connected(2, 3)
        System.out.println(uf.connected(2, 3)); // Expected output: true

        System.out.println("Test case 3: union(4, 5)");
        uf.union(4, 5); // Expected output: connected(4, 5)
        System.out.println(uf.connected(4, 5)); // Expected output: true

        System.out.println("Test case 4: union(0, 2)");
        uf.union(0, 2); // Expected output: connected(0, 2)
        System.out.println(uf.connected(0, 2)); // Expected output: true
    }
}