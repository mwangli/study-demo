package mwang.online.hot100;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/9 10:15
 * @description: No200_IslandNums
 * DFS深度优先搜索(树、图）
 */
public class No200_IslandNums {

    public static void main(String[] args) {
        final char[][] chars = new char[][]{
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        print(chars);
        System.out.println(numIslands(chars));
    }

    public static int numIslands(char[][] grid) {
        int nums = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    nums++;
                }
            }
        }
        return nums;
    }

    public static void dfs(char[][] grid, int row, int col) {
        if (!inArea(grid, row, col)) return;
        if (grid[row][col] == '1') {
            grid[row][col] = '2';
            dfs(grid, row - 1, col);
            dfs(grid, row + 1, col);
            dfs(grid, row, col - 1);
            dfs(grid, row, col + 1);
        }
    }

    public static boolean inArea(char[][] grid, int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }

    public static void print(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.printf("%4s", grid[i][j]);
            }
            System.out.println();
        }
    }
}
