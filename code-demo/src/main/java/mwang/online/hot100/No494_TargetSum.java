package mwang.online.hot100;

import java.util.Arrays;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/4/28 09:19
 * @description: No494_TargetSum
 * 思路：1.DFS 2. 0-1背包
 */
public class No494_TargetSum {

    private static int count = 0;

    public static void main(String[] args) {
        final int[] ints = {11};
        System.out.println(findTargetSumWays2(ints, 11));
    }

    public static int findTargetSumWays(int[] nums, int target) {
        dfs(nums, 0, target, 0);
        return count;
    }


    public static void dfs(int[] nums, int index, int target, int sum) {
        if (index == nums.length) {
            if (sum == target) count++;
        } else {
            final int num = nums[index];
            dfs(nums, index + 1, target, sum + num);
            dfs(nums, index + 1, target, sum - num);
        }
    }

    public static int findTargetSumWays2(int[] nums, int target) {
        final int sum = Arrays.stream(nums).reduce(Integer::sum).getAsInt();
        final int diff = sum - target;
        if (diff % 2 != 0 || diff < 0) return 0;
        int need = diff / 2;
        int n = nums.length;
        final int[][] dp = new int[n + 1][need + 1];
        dp[0][0] = 1;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                int index = i - 1;
                if (nums[index] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[index]];
                }
            }
        }
        return dp[n][need];
    }
}
