package mwang.online.hot100;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/23 09:26
 * @description: No221_MaxSquareArea
 * 动态规划，dp[i][j]表示以(i,j)为右下角的最大正方形边长
 * 当前位置值为1时，边长的值为其上，左，左上三个位置的边长最小值 + 1，边界情况除外
 * 转移方程：dp[i][j] = min{dp[i-1][j],dp[i,j-1],dp[i-1][j-1]} + 1
 */
public class No221_MaxSquareArea {

    public static void main(String[] args) {
        final char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};
        final int res = maximalSquare(matrix);
        System.out.println(res);
    }

    public static int maximalSquare(char[][] matrix) {
        int maxDp = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        final int[][] dp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxDp = Math.max(maxDp, dp[i][j]);
                }
            }
        }
        return maxDp * maxDp;
    }
}


