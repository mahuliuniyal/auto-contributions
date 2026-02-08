// Union-Find Data Structure Implementation in Java
public class UnionFind {
    private int[] parent; // Stores the parent of each element
    private int[] rank;   // Stores the rank of each element
    private int numSets;

    public UnionFind(int n) { // Initializes the data structure with n elements
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
        numSets = n;
    }

    // Finds the root of a set containing an element
    private int find(int x) {
        if (parent[x] != x) { // If x is not its own parent, it's in another set
            parent[x] = find(parent[x]); // Find the root and update the parent
        }
        return parent[x]; // Return the root of the set
    }

    // Merges two sets containing elements x and y
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) { // If the sets are not the same, merge them
            numSets--;
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX; // Make rootX the new parent of rootY
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY; // Make rootY the new parent of rootX
            } else { // If the ranks are equal, make one root a child of the other
                parent[rootX] = rootY;
                rank[rootY]++;
            }
        }
    }

    public int numSets() {
        return numSets;
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);
        System.out.println("Number of sets: " + uf.numSets());
        uf.union(0, 1);
        uf.union(2, 3);
        System.out.println("Number of sets after union(0,1): " + uf.numSets());
        uf.union(5, 6);
        System.out.println("Number of sets after union(5,6): " + uf.numSets());
    }
}