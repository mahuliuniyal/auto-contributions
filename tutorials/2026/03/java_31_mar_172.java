import java.util.Arrays;

public class UnionFind {

    // Number of vertices in the graph
    private int numVertices;
    
    // Array to store parent array for union operation
    private int[] parent;
    
    // Array to store rank array for union operation
    private int[] rank;

    /**
     * Constructor to initialize number of vertices and parent/rank arrays.
     *
     * @param n the number of vertices in the graph
     */
    public UnionFind(int n) {
        numVertices = n;
        parent = new int[numVertices];
        rank = new int[numVertices];

        // Initialize each vertex with itself as its parent
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    /**
     * Finds the root of a set.
     *
     * @param i the vertex to find the root of
     * @return the root of the set containing i
     */
    private int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    /**
     * Unites two sets by making one set the parent of another.
     *
     * @param i   the first vertex
     * @param j   the second vertex
     */
    public void union(int i, int j) {
        // Find roots of both sets
        int rootI = find(i);
        int rootJ = find(j);

        // If they are already in the same set, do nothing
        if (rootI == rootJ) {
            return;
        }

        // Make one set the parent of the other
        if (rank[rootI] < rank[rootJ]) {
            parent[rootI] = rootJ;
        } else if (rank[rootI] > rank[rootJ]) {
            parent[rootJ] = rootI;
        } else {
            parent[rootJ] = rootI;
            // Increment the rank of both sets
            rank[rootI]++;
        }
    }

    /**
     * Checks whether two vertices are in the same set.
     *
     * @param i   the first vertex
     * @param j   the second vertex
     * @return true if they are in the same set, false otherwise
     */
    public boolean isConnected(int i, int j) {
        return find(i) == find(j);
    }

    /**
     * Prints the graph.
     *
     * @param i the vertex to print
     */
    public void printGraph(int i) {
        // Print vertices and their connections
        for (int j = 0; j < numVertices; j++) {
            if (i == j || isConnected(i, j)) {
                System.out.print(j + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Create a new graph with 10 vertices
        UnionFind uf = new UnionFind(10);

        // Initialize the graph
        uf.parent[0] = 0;
        uf.parent[1] = 1;
        uf.parent[2] = 3;
        uf.parent[4] = 5;
        uf.parent[6] = 7;

        // Connect vertices
        uf.union(4, 5);