import java.util.*;

public class TopologicalSort {
    // Adjacency List Representation of a Graph
    private static class Graph {
        private int vertices;
        private List<List<Integer>> adjacencyList;

        public Graph(int vertices) {
            this.vertices = vertices;
            this.adjacencyList = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                this.adjacencyList.add(new ArrayList<>());
            }
        }

        // Add an edge between two vertices
        public void addEdge(int u, int v) {
            this.adjacencyList.get(u).add(v);
        }

        // Perform Topological Sort using DFS
        public void topologicalSort() {
            boolean[] visited = new boolean[vertices];
            int[] stack = new int[vertices];

            for (int i = 0; i < vertices; i++) {
                if (!visited[i]) {
                    dfs(i, visited, stack);
                }
            }

            // Print the sorted vertices
            System.out.println("Topological Sort:");
            for (int i = 0; i < vertices; i++) {
                System.out.print(stack[i] + " ");
            }
        }

        // Depth-First Search (DFS) Algorithm
        private void dfs(int vertex, boolean[] visited, int[] stack) {
            visited[vertex] = true;

            for (int neighbor : adjacencyList.get(vertex)) {
                if (!visited[neighbor]) {
                    dfs(neighbor, visited, stack);
                }
            }

            stack[vertex] = vertex; // Push the current vertex onto the stack
        }
    }

    public static void main(String[] args) {
        // Create a graph with 5 vertices
        Graph graph = new Graph(5);

        // Add edges to the graph
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);

        // Perform topological sort
        graph.topologicalSort();
    }
}