import java.util.*;

public class GraphBFS {
    // Define the structure of a graph
    static class Graph {
        int V;
        List<List<Integer>> adj;

        public Graph(int vertices) {
            this.V = vertices;
            this.adj = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                this.adj.add(new ArrayList<>());
            }
        }

        // Add an edge between two vertices
        void addEdge(int v, int w) {
            this.adj.get(v).add(w);
        }

        // Perform BFS traversal of the graph starting from a given vertex
        void bfs(int s) {
            boolean[] visited = new boolean[V];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(s);

            while (!queue.isEmpty()) {
                int currentVertex = queue.poll();
                System.out.print(currentVertex + " ");

                // Mark the current vertex as visited
                visited[currentVertex - 1] = true;

                for (int neighbour : this.adj.get(currentVertex)) {
                    if (!visited[neighbour - 1]) {
                        queue.add(neighbour);
                    }
                }
            }

            System.out.println();
        }

    }

    public static void main(String[] args) {
        // Create a sample graph
        Graph g = new Graph(6);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 5);

        System.out.println("Graph Traversal (BFS):");
        g.bfs(1); // Start traversal from vertex 1
    }
}