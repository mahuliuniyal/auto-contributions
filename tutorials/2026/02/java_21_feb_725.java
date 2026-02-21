import java.util.*;

public class DijkstraShortestPath {
    // Define a class to represent a graph
    static class Graph {
        // Adjacency list representation of the graph
        private Map<Integer, List<Edge>> adjacencyList;

        // Constructor to initialize the graph
        public Graph(int numVertices) {
            adjacencyList = new HashMap<>();
            for (int i = 0; i < numVertices; i++) {
                adjacencyList.put(i, new ArrayList<>());
            }
        }

        // Method to add an edge to the graph
        public void addEdge(int source, int destination, int weight) {
            adjacencyList.get(source).add(new Edge(destination, weight));
            // Since the graph is undirected, add the reverse edge as well
            adjacencyList.get(destination).add(new Edge(source, weight));
        }

        // Method to find the shortest path using Dijkstra's algorithm
        public List<Integer> dijkstra(int source) {
            // Initialize the distance array and previous vertex array
            int[] distance = new int[adjacencyList.size()];
            int[] previous = new int[adjacencyList.size()];
            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[source] = 0;

            // Priority queue to store vertices to be processed
            PriorityQueue<Vertex> queue = new PriorityQueue<>();
            queue.add(new Vertex(source, 0));

            while (!queue.isEmpty()) {
                // Extract the vertex with the minimum distance from the queue
                Vertex currentVertex = queue.poll();
                int currentDistance = currentVertex.distance;

                // Iterate over the neighbors of the current vertex
                for (Edge edge : adjacencyList.get(currentVertex.vertex)) {
                    int neighbor = edge.destination;
                    int weight = edge.weight;

                    // Calculate the tentative distance to the neighbor
                    int tentativeDistance = currentDistance + weight;

                    // If the calculated distance is less than the current distance, update the distance and previous vertex
                    if (tentativeDistance < distance[neighbor]) {
                        distance[neighbor] = tentativeDistance;
                        previous[neighbor] = currentVertex.vertex;
                        queue.add(new Vertex(neighbor, tentativeDistance));
                    }
                }
            }

            // Build the shortest path by backtracking from the destination vertex
            List<Integer> shortestPath = new ArrayList<>();
            int currentVertex = source;
            while (currentVertex != -1) {
                shortestPath.add(currentVertex);
                currentVertex = previous[currentVertex];
            }

            // Reverse the shortest path to get it in the correct order
            Collections.reverse(shortestPath);

            return shortestPath;
        }
    }

    // Define a class to represent an edge in the graph
    static class Edge {
        int destination;
        int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    // Define a class to represent a vertex in the graph
    static class Vertex implements Comparable<Vertex> {
        int vertex;
        int distance;

        public Vertex(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Vertex other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static void main(String[] args) {
        // Create a graph with 5 vertices
        Graph graph = new Graph(5);

        //