import java.util.*;

public class DijkstraShortestPath {
    public static void main(String[] args) {
        int[][] graph = {
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        int startNode = 0;
        int endNode = 8;

        System.out.println("Shortest distance from node " + startNode + " to all other nodes:");
        System.out.println("Node\tDistance");
        for (int i = 0; i < graph.length; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int i = 0; i < graph.length; i++) {
            System.out.print(i + "\t" + dijkstra(graph, startNode, i) + "\t");
        }

        System.out.println("Shortest path from node " + startNode + " to node " + endNode + ":");
        System.out.print(startNode + " -> ");
        System.out.println(findShortestPath(graph, startNode, endNode));
    }

    public static int dijkstra(int[][] graph, int startNode, int endNode) {
        int[] shortestDistances = new int[graph.length];
        Arrays.fill(shortestDistances, Integer.MAX_VALUE);
        shortestDistances[startNode] = 0;

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Node(0, startNode));

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();
            int currentDistance = currentNode.distance;
            int currentNodeIndex = currentNode.nodeIndex;

            if (currentNodeIndex == endNode) {
                return currentDistance;
            }

            for (int i = 0; i < graph.length; i++) {
                if (graph[currentNodeIndex][i] > 0) {
                    int newDistance = currentDistance + graph[currentNodeIndex][i];

                    if (newDistance < shortestDistances[i]) {
                        shortestDistances[i] = newDistance;
                        priorityQueue.add(new Node(newDistance, i));
                    }
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    public static String findShortestPath(int[][] graph, int startNode