package mwang.online.hot100;

import java.util.Arrays;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/4/26 10:23
 * @description: No416_PartitionSet
 * 思路：动态规划
 * 从集合中选取元素，其和刚好为sum/2.
 * dp[i][j] 表示从集合中选取下标从0-i的元素，其和是否刚好为j。
 * 当num[i] <= j, num[i]可用，有选中活不选中两种情况
 * dp[i][j] = dp[i-1][j]
 * dp[i][j] = dp[i-1][j-nums[i]]
 * 当 num[i] > j nums[i] 不可选中
 * dp[i][j] = dp[i-1][j]
 */
public class No416_PartitionSet {

    public static void main(String[] args) {
        final int[] ints = {1, 5, 11, 5};
        System.out.println(canPartition(ints));
    }


    public static boolean canPartition(int[] nums) {
        if (nums.length < 2) return false;
        final int sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1) return false;
        final int target = sum / 2;
        final boolean[][] dp = new boolean[nums.length][target + 1];
        // 当j=0，有友任意可用元素时，不选中任何元素即可组成和为0
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }
        // 当i=0，只有nums[0]一个可用元素，j只能为nums[0]
        if (nums[0] <= target)
            dp[0][nums[0]] = true;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (nums[i] > j) {
                    // nums[i]不可用
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // nums[i]可用，可选择用或不用
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[nums.length - 1][target];
    }
}
