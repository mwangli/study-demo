package mwang.online.hot100;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/4/4 09:39
 * @description: No322_CoinChange
 * 思路：F(i)表示组成金额i的最少硬币数量，
 * 假设组成目标金额最后一枚硬币面值为j，F(i) = F(i-j)+1
 * 枚举j可能的取值，取最小值，则状态转移方程为
 * F(i) = min{F(i-j)+1}; j=[0,n]
 */
public class No322_CoinChange {

    public static void main(String[] args) {
        final int[] coins = {2};
        System.out.println(coinChange(coins, 3));
    }

    public static int coinChange(int[] coins, int amount) {
        final int[] dp = new int[amount + 1];
        // 用极大值表示不可达
        int NIF = Integer.MAX_VALUE;
        for (int i = 1; i <= amount; i++) {
            int min = NIF;
            // 枚举j的取值，获取最小值
            for (int coin : coins) {
                if (i - coin >= 0)
                    min = Math.min(min, dp[i - coin]);
            }
            dp[i] = min == NIF ? NIF : min + 1;
        }
        return dp[amount] == NIF ? -1 : dp[amount];
    }
}
