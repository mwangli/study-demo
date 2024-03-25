package mwang.online.hot100;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/4/3 14:02
 * @description: No309_MaxProfit
 * 思路：动态规划，f[i] 表示第i天的累计最大收益
 * f[i][0]表示 持有股票；
 * f[i][1] 表示不持有股票，且处于冷冻期
 * f[i][2] 表示不持有股票，且不处于冷冻期
 */
public class No309_MaxProfit {

    public static void main(String[] args) {
        final int[] ints = {1, 2, 3, 0, 2};
        System.out.println(maxProfit(ints));
    }

    public static int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int f0 = -prices[0];
        int f1 = 0;
        int f2 = 0;
        for (int i = 1; i < prices.length; i++) {
            int f00 = Math.max(f2 - prices[i], f0);
            int f11 = f0 + prices[i];
            int f22 = Math.max(f1, f2);
            f0 = f00;
            f1 = f11;
            f2 = f22;
        }
        return Math.max(f1, f2);
    }
}
