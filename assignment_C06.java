import java.util.*;

public class assignment_CO6 {

    // Returns minimum number of coins needed
    static int minCoins(int[] coins, int amount) {

        int[] dp = new int[amount + 1];

        Arrays.fill(dp, amount + 1);

        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {

            for (int c : coins) {

                if (c <= i && dp[i - c] != amount + 1) {

                    dp[i] = Math.min(dp[i],
                            dp[i - c] + 1);
                }
            }
        }

        return (dp[amount] == amount + 1)
                ? -1
                : dp[amount];
    }

    // Returns one optimal set of coins used
    static List<Integer> coinsUsed(int[] coins,
            int amount) {

        int[] dp = new int[amount + 1];

        int[] choice = new int[amount + 1];

        Arrays.fill(dp, amount + 1);

        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {

            for (int c : coins) {

                if (c <= i &&
                        dp[i - c] + 1 < dp[i]) {

                    dp[i] = dp[i - c] + 1;

                    choice[i] = c;
                }
            }
        }

        List<Integer> result = new ArrayList<>();

        if (dp[amount] == amount + 1) {

            return result;
        }

        while (amount > 0) {

            result.add(choice[amount]);

            amount -= choice[amount];
        }

        return result;
    }

    public static void main(String[] args) {

        int[] coins = { 1, 2, 5, 10, 20 };

        int amount = 43;

        int min = minCoins(coins, amount);

        List<Integer> used = coinsUsed(coins, amount);

        System.out.println(
                "Amount = " + amount);

        System.out.println(
                "Coins = " +
                        Arrays.toString(coins));

        System.out.println(
                "\nMinimum Coins Required = "
                        + min);

        System.out.println(
                "Coins Used = " + used);
    }
}
