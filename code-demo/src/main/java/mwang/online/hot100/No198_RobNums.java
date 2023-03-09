package mwang.online.hot100;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/9 09:38
 * @description: No198_RobNums
 * 动态规划状态转移方程:
 * i<=2
 * dp[0] = nums[0]
 * dp[1] = max{nums[0],nums[1]}
 * i>2
 * dp[i] = max{dp[i-2]+num[i], dp[i-1]}
 */
public class No198_RobNums {

    public static void main(String[] args) {
        final int[] ints = {2, 7, 9, 3, 1};
        System.out.println(rob(ints));
    }

    public static int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        final int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[dp.length - 1];
    }

    public static int rob2(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        // 当前状态之和前两个状态相关
        var first = nums[0];
        var second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int temp2 = second;
            second = Math.max(first + nums[i], temp2);
            first = temp2;
        }
        return second;
    }
}
