package mwang.online.classic150;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/4/16 10:31
 * @description: No73_MatrixZeros
 */
public class No73_MatrixZeros {

    @Test
    public void test() {
        int[][] matrix = new int[][]{
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}};
        showMatrix(matrix);
        System.out.println();
        setZeroes(matrix);
        showMatrix(matrix);
    }

    public void setZeroes(int[][] matrix) {
        int[] rowIndex = new int[matrix.length];
        int[] columnIndex = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rowIndex[i] = 1;
                    columnIndex[j] = 1;
                }
            }
        }
        for (int i = 0; i < rowIndex.length; i++) {
            if (rowIndex[i] == 1) {
                setRowZeros(matrix, i);
            }
        }
        for (int i = 0; i < columnIndex.length; i++) {
            if (columnIndex[i] == 1) {
                setColumnZeros(matrix, i);
            }
        }
    }

    private void setRowZeros(int[][] matrix, int rowIndex) {
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[rowIndex][i] = 0;
        }
    }

    private void setColumnZeros(int[][] matrix, int columnIndex) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][columnIndex] = 0;
        }
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
