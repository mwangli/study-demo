package mwang.online.classic150;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/3/13 13:43
 * @description: No63_UniquePath2
 */
public class No63_UniquePath2 {
    public static void main(String[] args) {
//        int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
//        int[][] grid = {{0, 1}, {0, 0}};
        int[][] grid = {{1, 0}};
        final int res = new No63_UniquePath2().uniquePathsWithObstacles(grid);
        System.out.println(res);
    }


    // DP
    // dp[i][j] = dp[i-1][j] +1;
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        AtomicInteger count = new AtomicInteger(0);
        dfs(obstacleGrid, 0, 0, count);
        return count.get();
    }

    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        AtomicInteger count = new AtomicInteger(0);
        dfs(obstacleGrid, 0, 0, count);
        return count.get();
    }

    // dfs search
    public boolean dfs(int[][] grid, int x, int y, AtomicInteger count) {
        int m = grid.length;
        int n = grid[0].length;
        // with obstacle
        if (grid[x][y] == 1) return false;
        // when finish
        if (x == m - 1 && y == n - 1) {
            count.incrementAndGet();
            return true;
        }
        // recursive search
        boolean path1 = false, path2 = false;
        if (x + 1 < m && grid[x + 1][y] < 1) {
            path1 = dfs(grid, x + 1, y, count);
        }
        if (y + 1 < n && grid[x][y + 1] < 1) {
            path2 = dfs(grid, x, y + 1, count);
        }
        return path1 | path2;
    }
}
