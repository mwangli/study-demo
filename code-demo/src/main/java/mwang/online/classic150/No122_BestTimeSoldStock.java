package mwang.online.classic150;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/3/14 09:42
 * @description: No122_BestTimeSoldStock
 */
public class No122_BestTimeSoldStock {

    public static void main(String[] args) {
        final int[] stocks = {7, 1, 5, 3, 6, 4};
        final int res = new No122_BestTimeSoldStock().maxProfit(stocks);
        System.out.println(res);
    }

    // Dynamic Plan  => only can hold at most one stock
    // dp[i][0] => on ith day,when not have stock, the max profit
    // dp[i][1] => that on ith day,when have a stock, the max profit
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            // when current day do not have stock, the previous day maybe have or not have stock
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}
