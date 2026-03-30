import java.util.*;

public class GraphTraversalBFS {

    // Define the Graph using an adjacency list representation
    static class Graph {
        int V;
        List<List<Integer>> adj;

        public Graph(int vertices) {
            V = vertices;
            adj = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
        }

        // Function to add an edge between two vertices
        void addEdge(int v, int w) {
            adj.get(v).add(w);
            adj.get(w).add(v); // Comment: To make the graph undirected, add the reverse edge
        }

        // Perform BFS traversal of the graph starting from a given source vertex
        void BFS(int s) {
            boolean[] visited = new boolean[V];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(s);
            visited[s] = true;

            while (!queue.isEmpty()) {
                int currentVertex = queue.poll();
                System.out.print(currentVertex + " ");

                // Visit all the adjacent vertices of the current vertex
                for (int neighbour : adj.get(currentVertex)) {
                    if (!visited[neighbour]) {
                        queue.add(neighbour);
                        visited[neighbour] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // Create a graph with 6 vertices
        Graph g = new Graph(6);

        // Add edges to the graph
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 5);

        System.out.println("Following is Breadth First Traversal:");
        g.BFS(0); // Start traversal from vertex 0
    }
}