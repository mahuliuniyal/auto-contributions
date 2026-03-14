// Dijkstra's Shortest Path Algorithm in Java

import java.util.*;

public class Dijkstra {
    // Class to represent a graph edge
    static class Edge {
        int destination;
        int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    // Method to implement Dijkstra's algorithm
    public static void dijkstra(Edge[] edges, int startNode, Map<Integer, Integer> distances) {
        // Create a set of all unvisited nodes
        Set<Integer> unvisitedNodes = new HashSet<>();
        for (int i = 0; i < distances.size(); i++) {
            unvisitedNodes.add(i);
        }

        // Initialize the distance to the start node as 0, and all other nodes as infinity
        for (Integer key : distances.keySet()) {
            distances.put(key, Integer.MAX_VALUE);
        }
        distances.put(startNode, 0);

        while (!unvisitedNodes.isEmpty()) {
            // Find the unvisited node with the smallest distance
            int currentNode = getSmallestDistanceNode(distances, unvisitedNodes);

            // If no node has been found, exit the algorithm
            if (currentNode == null) {
                break;
            }

            // Remove the current node from the set of unvisited nodes
            unvisitedNodes.remove(currentNode);

            // Update the distances to the neighboring nodes
            for (Edge edge : edges) {
                if (edge.destination == currentNode) {
                    int weight = edge.weight;
                    int distanceToNeighbor = distances.get(currentNode) + weight;

                    // If a shorter path to the neighbor has been found, update its distance
                    if (distanceToNeighbor < distances.get(edge.destination)) {
                        distances.put(edge.destination, distanceToNeighbor);
                    }
                }
            }
        }

        System.out.println("Shortest Distances:");
        for (Map.Entry<Integer, Integer> entry : distances.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    // Method to find the node with the smallest distance in a set
    public static int getSmallestDistanceNode(Map<Integer, Integer> distances, Set<Integer> unvisitedNodes) {
        int minKey = Integer.MAX_VALUE;
        for (Integer key : unvisitedNodes) {
            if (distances.get(key) < minKey) {
                minKey = key;
            }
        }

        return minKey == Integer.MAX_VALUE ? null : minKey;
    }

    // Method to print the edges of the graph
    public static void printEdges(Edge[] edges) {
        System.out.println("Graph Edges:");
        for (Edge edge : edges) {
            System.out.println(edge.destination + ": " + edge.weight);
        }
    }

    // Main method to test the Dijkstra algorithm
    public static void main(String[] args) {
        int numNodes = 5;
        Edge[] edges = new Edge[numNodes * (numNodes - 1) / 2];

        // Initialize the nodes and edges of the graph
        for (int i = 0; i < numNodes; i++) {
            edges[i] = new Edge(i, Integer.MAX_VALUE);
            edges[i + numNodes] = new Edge(i + 1, 4); // Node 1 to Node