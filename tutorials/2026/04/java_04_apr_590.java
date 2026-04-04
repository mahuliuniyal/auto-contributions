import java.util.*;

public class TopologicalSort {
    public static void main(String[] args) {
        List<String> graph = new ArrayList<>();
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.add("D");

        List<String> inDegree = new ArrayList<>();

        for (String node : graph) {
            inDegree.add(0);
        }

        addEdge(graph, inDegree);

        Queue<String> queue = new LinkedList<>();
        buildGraph(inDegree);

        while (!queue.isEmpty()) {
            String node = queue.poll();
            System.out.print(node + " ");
            for (int childIndex = 0; childIndex < graph.size(); childIndex++) {
                if (graph.get(childIndex).contains(node)) {
                    inDegree.set(childIndex, inDegree.get(childIndex) - 1);
                    if (inDegree.get(childIndex) == 0) {
                        queue.add(graph.get(childIndex));
                    }
                }
            }
        }

        System.out.println();
    }

    public static void addEdge(List<String> graph, List<Integer> inDegree) {
        String[] edges = { "AB", "AC", "AD", "BC", "BD", "CD" };
        for (String edge : edges) {
            if (edge.contains("A")) {
                graph.add(edge.replaceFirst("A", ""));
                inDegree.set(graph.indexOf(edge), inDegree.get(graph.indexOf(edge)) + 1);
            } else if (edge.contains("B")) {
                graph.add(edge.replaceFirst("B", ""));
                inDegree.set(graph.indexOf(edge), inDegree.get(graph.indexOf(edge)) + 1);
            } else if (edge.contains("C")) {
                graph.add(edge.replaceFirst("C", ""));
                inDegree.set(graph.indexOf(edge), inDegree.get(graph.indexOf(edge)) + 1);
            } else if (edge.contains("D")) {
                graph.add(edge.replaceFirst("D", ""));
                inDegree.set(graph.indexOf(edge), inDegree.get(graph.indexOf(edge)) + 1);
            }
        }
    }

    public static void buildGraph(List<Integer> inDegree) {
        for (int i = 0; i < inDegree.size(); i++) {
            if (inDegree.get(i) == 0) {
                queue.add(graph.get(i));
            }
        }
    }
}