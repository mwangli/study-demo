package mwang.online.hot100;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/8 14:50
 * @description: No152_MaxProduct
 * 152. 乘积最大子数组
 * 动态规划 dp[i]表示前i个元素的最大子数组乘积
 * dp[i] = max{dp[i-1]*num[i], nums[i]}
 * 问题： 连续
 */
public class No152_MaxProduct {

    public static void main(String[] args) {
        final int[] ints = {2, 3, -2, 4};
        System.out.println(maxProduct(ints));
    }

    public static int maxProduct(int[] nums) {
        final int[] maxDP = new int[nums.length + 1];
        final int[] minDP = new int[nums.length + 1];
        maxDP[0] = 1;
        minDP[0] = 1;
        int ans = nums[0];
        for (int i = 1; i < maxDP.length; i++) {
            maxDP[i] = Math.max(minDP[i - 1] * nums[i - 1], Math.max(maxDP[i - 1] * nums[i - 1], nums[i - 1]));
            minDP[i] = Math.min(maxDP[i - 1] * nums[i - 1], Math.min(minDP[i - 1] * nums[i - 1], nums[i - 1]));
            ans = Math.max(ans, maxDP[i]);
        }
        return ans;
    }
}
