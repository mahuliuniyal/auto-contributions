// UnionFind.java

public class UnionFind {
    // Number of elements in the universe
    private int n;
    // Parent array for each element
    private int[] parent;
    // Rank array for each connected component
    private int[] rank;

    public UnionFind(int n) {
        this.n = n;
        // Initialize parent and rank arrays with indices 0 to n-1
        parent = new int[n];
        rank = new int[n];

        // Set all elements as their own parent and rank to 0
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    // Find the root of a set containing x
    private int find(int x) {
        if (parent[x] != x) { // Not our own parent, find recursively
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Union the sets containing x and y
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) { // Not already in same set
            if (rank[rootX] > rank[rootY]) { // Make tree with more nodes our parent
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else { // Equal ranks, make one our parent and increment its rank
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(5);

        System.out.println("Initial parent array:");
        for (int i = 0; i < 5; i++) {
            System.out.print(uf.parent[i] + " ");
        }
        System.out.println();

        // Union sets containing 0 and 1
        uf.union(0, 1);
        System.out.println("After union of 0 and 1:");
        for (int i = 0; i < 5; i++) {
            System.out.print(uf.parent[i] + " ");
        }
        System.out.println();

        // Union sets containing 2 and 3
        uf.union(2, 3);
        System.out.println("After union of 2 and 3:");
        for (int i = 0; i < 5; i++) {
            System.out.print(uf.parent[i] + " ");
        }
        System.out.println();

        // Check if sets containing 1 and 4 are the same
        System.out.println("Is set containing 1 equal to set containing 4? " + (uf.find(1) == uf.find(4)));
    }
}