package mwang.online.hot100;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/4/3 10:47
 * @description: No300_LongestIncrementSubString
 * 思路：动态规划，dp[i]表示以nums[i]结尾的最长上升子序列长度
 * 当nums[i] > nums[j] dp[i] = max{dp[j]} + 1;  j [0,i-1]
 * ans = max{dp[0,n]}
 */
public class No300_LongestIncrementSubString {

    public static void main(String[] args) {
//        final int[] ints = {10, 9, 2, 5, 3, 7, 101, 18};
//        final int[] ints = {7, 7, 7, 7, 7, 7, 7};
        final int[] ints = {4, 10, 4, 3, 8, 9};
        System.out.println(lengthOfLIS(ints));
    }

    public static int lengthOfLIS(int[] nums) {
        final int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            res = Math.max(dp[i], res);
        }
        return res;
    }
}
