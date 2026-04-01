import java.util.*;

public class TopologicalSort {

    // Function to perform the topological sort
    public static List<String> topologicalSort(List<List<Integer>> graph) {
        int V = graph.size();
        boolean[] visited = new boolean[V];
        List<String> res = new ArrayList<>();

        // Perform DFS on each unvisited node
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(graph, i, visited, res);
            }
        }

        return res;
    }

    // Helper function to perform the depth-first search
    private static void dfs(List<List<Integer>> graph, int node, boolean[] visited, List<String> res) {
        visited[node] = true;

        // Visit all the neighbors of the current node
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(graph, neighbor, visited, res);
            }
        }

        // Add the current node to the result after visiting all its neighbors
        res.add(String.valueOf(node));
    }

    public static void main(String[] args) {
        List<List<Integer>> graph = new ArrayList<>();
        graph.add(Arrays.asList(1, 2));
        graph.add(Arrays.asList(3, 4));
        graph.add(Arrays.asList(5, 6));

        System.out.println("Topological Sort: " + topologicalSort(graph));
    }
}