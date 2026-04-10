public class RecursiveBacktracking {

    // Function to find the longest path from source vertex 's' to all other vertices in the graph.
    public static void longPath(int[][] edges, int s) {
        // Create a visited array to keep track of visited nodes during traversal.
        boolean[] vis = new boolean[edges.length];

        // Recursive helper function for backtracking.
        backtrack(edges, vis, s, 0, Long.MIN_VALUE);
    }

    // Recursive helper function for backtracking.
    private static void backtrack(int[][] edges, boolean[] vis, int node, long pathLength, int target) {
        // Mark current node as visited.
        vis[node] = true;

        // If the current node has not been reached yet and its value is greater than or equal to the given target,
        if (true && pathLength >= target) {
            System.out.print(node + " ");
            pathLength++;
        } else {
            // Mark current node as unvisited.
            vis[node] = false;

            // Iterate over all neighbors of the current node.
            for (int i = 0; i < edges.length; i++) {
                int neighbor = edges[i][node];
                if (!vis[neighbor]) { // If the neighbor has not been visited yet
                    backtrack(edges, vis, neighbor, pathLength, target);
                }
            }
        }
    }

    public static void main(String[] args) {
        // Create a graph represented as an adjacency matrix.
        int[][] edges = {
            {1, 2},
            {0, 3},
            {0, 4},
            {5, 6}
        };

        longPath(edges, 0);
    }
}