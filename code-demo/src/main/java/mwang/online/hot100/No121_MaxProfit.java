package mwang.online.hot100;

/**
 * 121. 买卖股票的最佳时机
 * 动态规划: F(i)为第i日迈出的利润
 * F(i) = F(i-1)
 */
public class No121_MaxProfit {

    public static void main(String[] args) {
        System.out.println(dp(new int[]{7, 1, 5, 3, 6, 4}));
    }

    public static int dp(int[] prices) {
        int[] dp = new int[prices.length];
        dp[0] = 0;
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            dp[i] = Math.max(dp[i - 1], prices[i] - minPrice);
        }
        return dp[prices.length - 1];
    }

    public static int maxProfit2(int[] prices) {
        int maxPrice = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i; j < prices.length; j++) {
                maxPrice = Math.max(maxPrice, prices[j] - prices[i]);
            }
        }
        return maxPrice;
    }


    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            if (price < minPrice) {
                // 寻找最低点
                minPrice = price;
            } else {
                // 保存最大值
                maxProfit = Math.max(maxProfit, (price - minPrice));
            }
        }
        return maxProfit;
    }
}
