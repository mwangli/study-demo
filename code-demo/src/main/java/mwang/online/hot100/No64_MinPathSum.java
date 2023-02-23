package mwang.online.hot100;

/**
 * 64. 最小路径和
 * https://leetcode.cn/problems/minimum-path-sum/description/
 * 思路：1.动态规划 状态转移方程
 * dp(i,j) = Min{ dp(i-1,j)+num[i][j], dp(i,j-1)+num[i][j] }
 * res = dp(m ,n) ; dp(0,0) = 0;
 * dp(i,0) = dp(i-1,0) + num(i,0)
 * dp(0,j) = dp(0,j-1) + num(0,j)
 */
public class No64_MinPathSum {

    public static void main(String[] args) {
        No64_MinPathSum no64_minPathSum = new No64_MinPathSum();
        int[][] ints = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}};
        int res = no64_minPathSum.minPathSum(ints);
        System.out.println(res);
    }

    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols];
        // 初始化第一行和第一列
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < cols; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        // DP转移
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                dp[i][j] = Math.min(dp[i - 1][j] + grid[i][j], dp[i][j - 1] + grid[i][j]);
            }
        }
        return dp[rows - 1][cols - 1];
    }
}
