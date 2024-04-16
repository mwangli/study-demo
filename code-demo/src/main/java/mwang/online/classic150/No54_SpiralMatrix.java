package mwang.online.classic150;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/4/16 09:51
 * @description: No54_SpiralMatrix
 */
public class No54_SpiralMatrix {

    @Test
    public void test() {
        int[][] matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        final List<Integer> res = spiralOrder(matrix);
        System.out.println(res);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        final ArrayList<Integer> result = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] visited = new int[m][n];
        int i = 0, j = 0;
        result.add(matrix[i][j]);
        visited[i][j]=1;
        while (result.size() < m * n) {
            // to right
           while (j + 1 < n && visited[i][j + 1] == 0) {
                result.add(matrix[i][j + 1]);
                visited[i][j + 1] = 1;
                j++;
            }
            // to down
            while (i + 1 < m && visited[i + 1][j] == 0) {
                result.add(matrix[i + 1][j]);
                visited[i + 1][j] = 1;
                i++;
            }
            // to left
            while (j - 1 >= 0 && visited[i][j - 1] == 0) {
                result.add(matrix[i][j - 1]);
                visited[i][j - 1] = 1;
                j--;
            }
            // to up
            while (i - 1 >= 0 && visited[i - 1][j] == 0) {
                result.add(matrix[i - 1][j]);
                visited[i - 1][j] = 1;
                i--;
            }
        }
        return result;
    }
}
