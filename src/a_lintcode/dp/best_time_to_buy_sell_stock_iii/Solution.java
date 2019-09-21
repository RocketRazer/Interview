package a_lintcode.dp.best_time_to_buy_sell_stock_iii;

public class Solution {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int n = prices.length;
        // five stage method;
        // f[i][j] 表示前i天（i-1天结束后），处于j阶段的最大盈利
        int[][] f = new int[n+1][5+1];

        //init
        f[0][1] = 0;
        for(int j = 2; j < 6; j++) {
            // 第0天不可能出去其他阶段
            f[0][j] = Integer.MIN_VALUE;
        }


        for (int i  = 1; i <= n; i++) {
            //阶段1, 3, 5最终状态没有持有股票: f[i][j] = max{f[i-1][j], f[i-1][j-1] + Pi-1 – Pi-2}
            // max {（前一天也没有持有股票，相同利润），（前一天持有股票， 几天卖出）}
            for (int j = 1; j <= 5; j += 2) {
                f[i][j] = f[i-1][j];
                if (j > 1 && i >= 2 && f[i-1][j-1] != Integer.MIN_VALUE) {
                    f[i][j] = Math.max(f[i-1][j], f[i-1][j-1] + prices[i-1] - prices[i-2]);
                }
            }


            //阶段2， 4 手中持有股票状态： f[i][j] = max{f[i-1][j] + Pi-1 – Pi-2, f[i-1][j-1]}
            // max { （昨天持有股票，今天继续持有并获利）， （昨天没有持有股票， 今天买入）}
            for (int j = 2; j < 5; j += 2) {
                f[i][j] = f[i-1][j-1];
                if (i > 1 && f[i-1][j-1] != Integer.MIN_VALUE) {
                    f[i][j] = Math.max(f[i-1][j] + prices[i-1] - prices[i-2], f[i-1][j-1]);
                }
            }
        }

        return Math.max(f[n][1], Math.max(f[n][3], f[n][5]));
    }
}