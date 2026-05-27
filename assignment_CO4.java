import java.util.*;

class StationLine {

    String station;
    int line; // 0 = Red, 1 = Blue, 2 = Green

    StationLine(String station, int line) {
        this.station = station;
        this.line = line;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (!(o instanceof StationLine))
            return false;

        StationLine other = (StationLine) o;

        return line == other.line &&
                station.equals(other.station);
    }

    @Override
    public int hashCode() {

        return Objects.hash(station, line);
    }

    @Override
    public String toString() {

        String lineName = "";

        if (line == 0)
            lineName = "Red";

        else if (line == 1)
            lineName = "Blue";

        else
            lineName = "Green";

        return station + " (" + lineName + ")";
    }
}

class MetroEdge {

    StationLine to;
    int weight;

    MetroEdge(StationLine to, int weight) {

        this.to = to;
        this.weight = weight;
    }
}

public class assignment_CO4 {

    static final int TRANSFER_PENALTY = 5;

    // Add edge to graph
    static void addEdge(
            Map<StationLine, List<MetroEdge>> graph,

            StationLine from,
            StationLine to,
            int weight) {

        graph.putIfAbsent(from,
                new ArrayList<>());

        graph.get(from)
                .add(new MetroEdge(to, weight));
    }

    // Dijkstra Algorithm
    static int shortestTime(
            Map<StationLine, List<MetroEdge>> adj,

            StationLine src,
            StationLine dst) {

        Map<StationLine, Integer> dist = new HashMap<>();

        PriorityQueue<Object[]> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(
                        (int) a[0],
                        (int) b[0]));

        dist.put(src, 0);

        pq.offer(new Object[] { 0, src });

        while (!pq.isEmpty()) {

            Object[] top = pq.poll();

            int d = (int) top[0];

            StationLine u = (StationLine) top[1];

            // Skip stale entries

            if (d > dist.getOrDefault(
                    u,
                    Integer.MAX_VALUE)) {

                continue;
            }

            // Destination reached

            if (u.equals(dst)) {

                return d;
            }

            // Relax neighbors

            for (MetroEdge e : adj.getOrDefault(
                    u,
                    new ArrayList<>())) {

                int newDist = d + e.weight;

                if (newDist < dist.getOrDefault(
                        e.to,
                        Integer.MAX_VALUE)) {

                    dist.put(e.to,
                            newDist);

                    pq.offer(
                            new Object[] {
                                    newDist,
                                    e.to
                            });
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        Map<StationLine, List<MetroEdge>> graph = new HashMap<>();

        /*
         * Virtual Nodes
         */

        // Red Line

        StationLine LBN_R = new StationLine("LBN", 0);

        StationLine AMP_R = new StationLine("AMP", 0);

        StationLine MHM_R = new StationLine("MHM", 0);

        StationLine SCB_R = new StationLine("SCB", 0);

        // Blue Line

        StationLine AMP_B = new StationLine("AMP", 1);

        StationLine BGP_B = new StationLine("BGP", 1);

        StationLine KKP_B = new StationLine("KKP", 1);

        StationLine HTC_B = new StationLine("HTC", 1);

        // Green Line

        StationLine AMP_G = new StationLine("AMP", 2);

        StationLine KKP_G = new StationLine("KKP", 2);

        /*
         * Red Line Edges
         */

        addEdge(graph, LBN_R, AMP_R, 8);
        addEdge(graph, AMP_R, LBN_R, 8);

        addEdge(graph, AMP_R, MHM_R, 6);
        addEdge(graph, MHM_R, AMP_R, 6);

        addEdge(graph, MHM_R, SCB_R, 10);
        addEdge(graph, SCB_R, MHM_R, 10);

        /*
         * Blue Line Edges
         */

        addEdge(graph, AMP_B, BGP_B, 7);
        addEdge(graph, BGP_B, AMP_B, 7);

        addEdge(graph, BGP_B, KKP_B, 9);
        addEdge(graph, KKP_B, BGP_B, 9);

        addEdge(graph, KKP_B, HTC_B, 12);
        addEdge(graph, HTC_B, KKP_B, 12);

        /*
         * Green Line Edges
         */

        addEdge(graph, AMP_G, KKP_G, 15);
        addEdge(graph, KKP_G, AMP_G, 15);

        /*
         * Transfer Edges
         * Penalty = 5 min
         */

        // AMP interchange

        addEdge(graph, AMP_R, AMP_B,
                TRANSFER_PENALTY);

        addEdge(graph, AMP_B, AMP_R,
                TRANSFER_PENALTY);

        addEdge(graph, AMP_R, AMP_G,
                TRANSFER_PENALTY);

        addEdge(graph, AMP_G, AMP_R,
                TRANSFER_PENALTY);

        addEdge(graph, AMP_B, AMP_G,
                TRANSFER_PENALTY);

        addEdge(graph, AMP_G, AMP_B,
                TRANSFER_PENALTY);

        // KKP interchange

        addEdge(graph, KKP_B, KKP_G,
                TRANSFER_PENALTY);

        addEdge(graph, KKP_G, KKP_B,
                TRANSFER_PENALTY);

        /*
         * Run Dijkstra
         */

        int shortest = shortestTime(
                graph,
                LBN_R,
                HTC_B);

        System.out.println(
                "\n===== HYDERABAD METRO =====");

        System.out.println(
                "\nShortest Effective Time:");

        System.out.println(
                "LBN -> HTC = "
                        + shortest
                        + " minutes");
    }
}