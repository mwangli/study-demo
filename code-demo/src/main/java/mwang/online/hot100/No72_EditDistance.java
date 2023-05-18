package mwang.online.hot100;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/5/18 10:27
 * @description: No72_EditDistance
 * 思路：动态规划：
 * dp[i][j]代表 word1[0,i]与字符串word2[0,j]的距离
 * dp[i][j] = dp[i-1][j]+1, dp[i][j-1]+1 , dp[i-1][j-1] + 0/1
 * dp[i-1][j] 在word2尾部添加word1[i]，即可相同
 * dp[i][j-1] 在word1尾部添加word2[j]，即可相同
 * dp[i-1][j-1] 当word1[i]==word1[j]时，无需处理，不等时，进行修改操作
 */
public class No72_EditDistance {

    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros"));
    }


    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        // 空字符串返回对应长度
        if (m * n == 0) return m > 0 ? m : n;
        // DP
        final int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                final char w1 = word1.charAt(i - 1);
                final char w2 = word2.charAt(j - 1);
                final int dis = dp[i - 1][j - 1] + (w1 == w2 ? 0 : 1);
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dis);
            }
        }
        return dp[m][n];
    }
}
