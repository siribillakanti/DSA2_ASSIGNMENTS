import java.util.*;

class Record {
    int userId;
    String timestamp;

    Record(int userId, String timestamp) {
        this.userId = userId;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "(" + userId + ", " + timestamp + ")";
    }
}

public class assignment_CO2 {

    /*
     * Composite Index Simulation:
     * (user_id, timestamp)
     *
     * TreeMap keeps user_id sorted.
     * TreeSet keeps timestamps sorted for each user.
     */

    static TreeMap<Integer, TreeSet<String>> compositeIndex = new TreeMap<>();

    // Insert record into composite index
    static void insert(int userId, String timestamp) {

        compositeIndex.putIfAbsent(userId, new TreeSet<>());

        compositeIndex.get(userId).add(timestamp);
    }

    // Query 1:
    // Exact user_id + timestamp range
    static void queryUserAndTimeRange(
            int userId,
            String startDate,
            String endDate) {

        System.out.println(
                "\n===== QUERY 1 : user_id + timestamp range =====");

        if (!compositeIndex.containsKey(userId)) {
            System.out.println("No records found.");
            return;
        }

        TreeSet<String> timestamps = compositeIndex.get(userId);

        boolean found = false;

        for (String ts : timestamps) {

            if (ts.compareTo(startDate) >= 0 &&
                    ts.compareTo(endDate) <= 0) {

                System.out.println(
                        "User ID : " + userId +
                                " | Timestamp : " + ts);

                found = true;
            }
        }

        if (!found) {
            System.out.println("No records found in range.");
        }
    }

    // Query 2:
    // Search using user_id only
    static void queryUserOnly(int userId) {

        System.out.println(
                "\n===== QUERY 2 : user_id only =====");

        if (!compositeIndex.containsKey(userId)) {
            System.out.println("No records found.");
            return;
        }

        for (String ts : compositeIndex.get(userId)) {

            System.out.println(
                    "User ID : " + userId +
                            " | Timestamp : " + ts);
        }
    }

    // Query 3:
    // Search using timestamp only
    // Requires full index scan
    static void queryTimestampOnly(String targetTimestamp) {

        System.out.println(
                "\n===== QUERY 3 : timestamp only =====");

        boolean found = false;

        // Full index scan
        for (Map.Entry<Integer, TreeSet<String>> entry : compositeIndex.entrySet()) {

            int userId = entry.getKey();

            for (String ts : entry.getValue()) {

                if (ts.equals(targetTimestamp)) {

                    System.out.println(
                            "User ID : " + userId +
                                    " | Timestamp : " + ts);

                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No records found.");
        }
    }

    public static void main(String[] args) {

        // Inserting sample records

        insert(42, "2024-01-01");
        insert(42, "2024-01-10");
        insert(42, "2024-01-25");

        insert(10, "2024-01-05");
        insert(10, "2024-01-15");

        insert(55, "2024-01-20");

        // Query 1:
        // user_id + timestamp range

        queryUserAndTimeRange(
                42,
                "2024-01-01",
                "2024-01-31");

        // Query 2:
        // user_id only

        queryUserOnly(42);

        // Query 3:
        // timestamp only

        queryTimestampOnly("2024-01-20");
    }
}
