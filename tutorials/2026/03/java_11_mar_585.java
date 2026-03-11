import java.util.*;

public class Dijkstra {
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
        List<City> cities = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            cities.add(new City(i));
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (graph[i][j] != 0) {
                    cities.get(i).addNeighbor(cities.get(j), graph[i][j]);
                }
            }
        }
        List<City> shortestPaths = dijkstra(graph, cities.get(0));
        for (City city : shortestPaths) {
            System.out.println(city.name + ": " + city.distance);
        }
    }

    public static List<City> dijkstra(int[][] graph, City start) {
        Set<City> unvisited = new HashSet<>(Arrays.asList(graph[0].length));
        for (City city : graph[0]) {
            unvisited.add(city);
        }
        Map<City, Double> distances = new HashMap<>();
        for (City city : unvisited) {
            distances.put(city, Double.POSITIVE_INFINITY);
        }
        distances.put(start, 0.0);
        while (!unvisited.isEmpty()) {
            City current = getClosestCity(unvisited, distances);
            unvisited.remove(current);
            for (Neighbor neighbor : current.neighbors) {
                double newDistance = distances.get(current) + neighbor.weight;
                if (newDistance < distances.get(neighbor.city)) {
                    distances.put(neighbor.city, newDistance);
                }
            }
        }
        List<City> shortestPaths = new ArrayList<>();
        City current = start;
        while (current != null) {
            shortestPaths.add(current);
            City next = getClosestCity(new HashSet<>(Arrays.asList(current, shortestPaths)), distances);
            current = next;
        }
        Collections.reverse(shortestPaths);
        return shortestPaths;
    }

    public static City getClosestCity(Set<City> unvisited, Map