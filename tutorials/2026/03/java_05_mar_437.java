import java.util.*;

public class DijkstraShortestPath {

    // Function to find the shortest distance from the source to all other vertices
    public static void dijkstra(int[][] graph, int src) {
        int numVertices = graph.length;
        int[] dist = new int[numVertices];
        boolean[] visited = new boolean[numVertices];

        // Initialize distance array with infinity for all vertices
        for (int i = 0; i < numVertices; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        // Distance to the source vertex is 0
        dist[src] = 0;

        // Create a priority queue to store vertices to be processed
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[] { 0, src });

        while (!pq.isEmpty()) {
            int[] vertex = pq.poll();
            int d = vertex[0];
            int u = vertex[1];

            // If the current distance is greater than the already found distance, skip this vertex
            if (d > dist[u]) {
                continue;
            }

            // Mark the current vertex as visited
            visited[u] = true;

            // Update the distances of the adjacent vertices
            for (int v = 0; v < numVertices; v++) {
                if (graph[u][v] != 0 && !visited[v] && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    pq.add(new int[] { dist[v], v });
                }
            }
        }

        // Print the shortest distances
        System.out.println("Vertex\t\tDistance");
        for (int i = 0; i < numVertices; i++) {
            System.out.println(i + "\t\t" + dist[i]);
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
            { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
            { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
            { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
            { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
            { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
            { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
            { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
            { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
            { 0, 0, 2, 0, 0, 0, 6, 7, 0 }
        };

        int src = 0;
        System.out.println("