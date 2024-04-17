package mwang.online.classic150;

import org.junit.Test;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/4/17 09:41
 * @description: No289_GameOfLive
 */
public class No289_GameOfLive {

    @Test
    public void test() {
        int[][] case1 = new int[][]{
                {1, 1}, {1, 0}
        };
        showMatrix(case1);
        System.out.println("------");
        gameOfLife(case1);
        showMatrix(case1);
    }


    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        final int[][] boardCopy = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boardCopy[i][j] = board[i][j];
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                final int aliveNeighborsCount = countNeighborsLive(boardCopy, i, j);
                if (board[i][j] == 1 && aliveNeighborsCount < 2) board[i][j] = 0;
//                if (board[i][j] == 1 && (aliveNeighborsCount == 2 || aliveNeighborsCount == 3)) ; // do nothing
                if (board[i][j] == 1 && aliveNeighborsCount > 3) board[i][j] = 0;
                if (board[i][j] == 0 && aliveNeighborsCount == 3) board[i][j] = 1;

            }
        }
    }

    private int countNeighborsLive(int[][] board, int i, int j) {
        int m = board.length;
        int n = board[0].length;
        int count = 0;
        if (i + 1 < m) count += board[i + 1][j];
        if (i - 1 >= 0) count += board[i - 1][j];
        if (j + 1 < n) count += board[i][j + 1];
        if (j - 1 >= 0) count += board[i][j - 1];
        if (i + 1 < m && j + 1 < n) count += board[i + 1][j + 1];
        if (i - 1 >= 0 && j + 1 < n) count += board[i - 1][j + 1];
        if (i - 1 >= 0 && j - 1 >= 0) count += board[i - 1][j - 1];
        if (i + 1 < m && j - 1 >= 0) count += board[i + 1][j - 1];
        return count;
    }

    private void showMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
