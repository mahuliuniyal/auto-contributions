import java.util.*;

public class BfsGraphTraversal {
    // Define a Graph class to represent the graph data structure
    static class Graph {
        // Adjacency list representation of the graph
        private int vertices;
        private List<List<Integer>> adjList;

        public Graph(int vertices) {
            this.vertices = vertices;
            this.adjList = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        // Add an edge between two vertices
        public void addEdge(int src, int dest) {
            adjList.get(src).add(dest);
            adjList.get(dest).add(src); // For undirected graph
        }

        // Perform BFS traversal starting from the source vertex
        public void bfsTraversal(int src) {
            boolean[] visited = new boolean[vertices];
            Queue<Integer> queue = new LinkedList<>();

            // Mark the source vertex as visited
            visited[src] = true;
            queue.add(src);

            while (!queue.isEmpty()) {
                int currentVertex = queue.poll();
                System.out.print(currentVertex + " ");

                // Explore all adjacent vertices of the current vertex
                for (int neighbor : adjList.get(currentVertex)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.add(neighbor);
                    }
                }
            }
        }

        // Print the adjacency list representation of the graph
        public void printGraph() {
            System.out.println("Adjacency List Representation:");
            for (int i = 0; i < vertices; i++) {
                System.out.print(i + " -> ");
                for (int neighbor : adjList.get(i)) {
                    System.out.print(neighbor + " ");
                }
                System.out.println();
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
        g.addEdge(2, 4);
        g.addEdge(3, 5);

        // Print the adjacency list representation of the graph
        System.out.println("Graph:");
        g.printGraph();

        // Perform BFS traversal starting from vertex 0
        System.out.println("\nBFS Traversal:");
        g.bfsTraversal(0); // Output: 0 1 2 3 4 5
    }
}