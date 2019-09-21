package a_lintcode.dp.coin_change;

public class Solution {
    /**
     * @param coins: a list of integer
     * @param amount: a total amount of money amount
     * @return: the fewest number of coins that you need to make up
     */
    public int coinChange(int[] coins, int amount) {
        int[] f = new int[amount + 1];
        f[0] = 0;

        for (int i = 1; i <= amount; i++) {
            f[i] = Integer.MAX_VALUE;
            for (int k = 0; k < coins.length; k++) {
                if (i >= coins[k] && f[i - coins[k]] != Integer.MAX_VALUE) {
                    f[i] = Math.min(f[i], f[i - coins[k]] + 1);
                }
            }
        }

        if (f[amount] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return f[amount];
        }
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        Solution s = new Solution();
        System.out.println(s.coinChange(coins, 11));
    }
}