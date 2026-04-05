// Breadth First Search (BFS) Graph Traversal in Java
public class GraphTraversal {

    // Class to represent a graph node
    static class Node {
        int value;
        java.util.ArrayList<Node> neighbors;

        public Node(int value) {
            this.value = value;
            this.neighbors = new java.util.ArrayList<>();
        }
    }

    // Method to add an edge between two nodes in the graph
    public static void addEdge(Node node1, Node node2) {
        node1.neighbors.add(node2);
        node2.neighbors.add(node1);  // For undirected graph
    }

    // Method to perform BFS traversal of a graph starting from a given node
    public static void bfsTraversal(Node startNode) {
        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        java.util.HashSet<String> visitedNodes = new java.util.HashSet<>();

        queue.add(startNode);
        visitedNodes.add(String.valueOf(startNode.value));

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            System.out.print(currentNode.value + " ");

            for (Node neighbor : currentNode.neighbors) {
                if (!visitedNodes.contains(String.valueOf(neighbor.value))) {
                    queue.add(neighbor);
                    visitedNodes.add(String.valueOf(neighbor.value));
                }
            }
        }

        System.out.println("\nVisited nodes: " + visitedNodes);
    }

    // Main method to demonstrate BFS traversal
    public static void main(String[] args) {
        Node node1 = new Node(0);
        Node node2 = new Node(1);
        Node node3 = new Node(2);
        Node node4 = new Node(3);
        Node node5 = new Node(4);

        addEdge(node1, node2);   // Edge between node 0 and node 1
        addEdge(node1, node3);   // Edge between node 0 and node 2
        addEdge(node2, node4);   // Edge between node 1 and node 3
        addEdge(node3, node5);   // Edge between node 2 and node 4

        System.out.println("BFS Traversal:");
        bfsTraversal(node1);
    }
}