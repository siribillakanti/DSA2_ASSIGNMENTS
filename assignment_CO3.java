import java.util.*;

public class assignment_CO3 {

    // Graph using adjacency list
    static Map<String, List<String>> graph = new HashMap<>();

    // Add undirected edge
    static void addEdge(String source,
            String destination) {

        graph.putIfAbsent(source,
                new ArrayList<>());

        graph.putIfAbsent(destination,
                new ArrayList<>());

        graph.get(source).add(destination);

        graph.get(destination).add(source);
    }

    // BFS Traversal
    static void BFS(String startNode) {

        Queue<String> queue = new LinkedList<>();

        Set<String> visited = new HashSet<>();

        Map<String, Integer> hopCount = new LinkedHashMap<>();

        queue.add(startNode);

        visited.add(startNode);

        hopCount.put(startNode, 0);

        System.out.println(
                "\n===== BFS LEVEL ORDER EXPANSION =====");

        while (!queue.isEmpty()) {

            String current = queue.poll();

            System.out.println(
                    "\nCurrent Article : "
                            + current);

            for (String neighbor : graph.getOrDefault(
                    current,
                    new ArrayList<>())) {

                if (!visited.contains(neighbor)) {

                    visited.add(neighbor);

                    queue.add(neighbor);

                    hopCount.put(
                            neighbor,
                            hopCount.get(current) + 1);

                    System.out.println(
                            "Visited : "
                                    + neighbor
                                    + " | Hop Count : "
                                    + hopCount.get(neighbor));
                }
            }
        }

        // Display shortest hop counts

        System.out.println(
                "\n===== MINIMUM HOP COUNT FROM CRICKET =====");

        for (Map.Entry<String, Integer> entry : hopCount.entrySet()) {

            System.out.println(
                    "Cricket -> "
                            + entry.getKey()
                            + " = "
                            + entry.getValue()
                            + " hops");
        }
    }

    public static void main(String[] args) {

        // Edges from case study

        addEdge("Cricket", "India");
        addEdge("Cricket", "Sachin");

        addEdge("India", "Mumbai");
        addEdge("India", "Tendulkar");

        addEdge("Mumbai", "Wankhede");
        addEdge("Mumbai", "Bandra");
        addEdge("Mumbai", "MumbaiCity");

        addEdge("Sachin", "Tendulkar");
        addEdge("Sachin", "Wankhede");

        addEdge("Tendulkar", "Wankhede");

        addEdge("Bandra", "MumbaiCity");

        // Run BFS from Cricket

        BFS("Cricket");
    }
}