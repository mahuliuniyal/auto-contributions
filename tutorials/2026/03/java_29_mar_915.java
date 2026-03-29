import java.util.*;

public class BfsGraphTraversal {

    // Define a node in the graph
    static class Node {
        int value;
        List<Node> neighbors;

        public Node(int value) {
            this.value = value;
            this.neighbors = new ArrayList<>();
        }
    }

    // Perform BFS traversal on the given graph
    public static void bfsTraversal(Node startNode) {
        // Create a queue to hold nodes for processing
        Queue<Node> queue = new LinkedList<>();

        // Mark the start node as visited by adding it to the queue
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            // Print the value of the current node
            System.out.print(currentNode.value + " ");

            // Add all unvisited neighbors to the queue
            for (Node neighbor : currentNode.neighbors) {
                if (!neighbor.visited) {
                    neighbor.visited = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        // Create nodes and add edges to form a graph
        Node node1 = new Node(0);
        Node node2 = new Node(1);
        Node node3 = new Node(2);
        Node node4 = new Node(3);
        Node node5 = new Node(4);

        node1.neighbors.add(node2);
        node1.neighbors.add(node3);
        node2.neighbors.add(node4);
        node3.neighbors.add(node4);
        node4.neighbors.add(node5);

        // Mark all nodes as unvisited initially
        for (Node node : new Node[]{node1, node2, node3, node4, node5}) {
            node.visited = false;
        }

        System.out.println("BFS Traversal:");
        bfsTraversal(node1);
    }
}