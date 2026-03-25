public class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    // Find the root of a set
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Check if two sets are connected
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    // Union of two sets
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);

        // Create sets
        uf.union(1, 2);
        uf.union(3, 4);
        uf.union(5, 6);
        uf.union(7, 8);
        uf.union(9, 10);

        System.out.println("Is connected? " + uf.isConnected(1, 2)); // true
        System.out.println("Is connected? " + uf.isConnected(3, 4)); // true
        System.out.println("Is connected? " + uf.isConnected(5, 6)); // true
        System.out.println("Is connected? " + uf.isConnected(7, 8)); // true
        System.out.println("Is connected? " + uf.isConnected(9, 10)); // true

        uf.union(1, 3);
        uf.union(2, 4);

        System.out.println("Is connected? " + uf.isConnected(1, 2)); // false
        System.out.println("Is connected? " + uf.isConnected(1, 3)); // true
        System.out.println("Is connected? " + uf.isConnected(3, 4)); // true
    }
}