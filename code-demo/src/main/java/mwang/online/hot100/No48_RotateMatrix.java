package mwang.online.hot100;

/**
 * 48. 旋转图像
 * 对于矩阵中第 i 行的第 j 个元素，在旋转后，它出现在倒数第 i 列的第 j 个位置
 * matrix[row][col] => matrix_new[col][n−row−1]
 * 思路：1.使用另外一个矩阵来暂存
 * 2.先水平翻转再对角线交换 matrix[row][col] =>(水平轴翻转，列不变，行交换) matrix[n-row-1][col]
 * matrix[n-row-1][col]  => 对角线翻转(行转列，列转行) => matrix_new[col][n−row−1]
 * 扩展：任意角度的旋转都可以由翻转(水平或者垂直)加对角线交换得到
 */
public class No48_RotateMatrix {

    public static void main(String[] args) {
        int[][] ints = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        show(ints);
        System.out.println("-----------------");
        rotate2(ints);
        show(ints);
        System.out.println();
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] newMatrix = new int[n][n];
        for (var row = 0; row < n; row++) {
            for (var col = 0; col < n; col++) {
                newMatrix[col][n - row - 1] = matrix[row][col];
            }
            show(newMatrix);
        }
        for (var row = 0; row < n; row++) {
            for (var col = 0; col < n; col++) {
                newMatrix[row][col] = matrix[row][col];
            }
        }
    }

    public static void rotate2(int[][] matrix) {
        int n = matrix.length;
        // 水平轴旋转
        for (var row = 0; row < n / 2; row++) {
            for (var col = 0; col < n; col++) {
                int temp = matrix[n - row - 1][col];
                matrix[n - row - 1][col] = matrix[row][col];
                matrix[row][col] = temp;
            }
        }
        // 对角线翻转
        for (var row = 0; row < n; row++) {
            for (var col = row + 1; col < n; col++) {
                int temp = matrix[col][row];
                matrix[col][row] = matrix[row][col];
                matrix[row][col] = temp;
            }
        }
    }

    public static void show(int[][] matrix) {
        for (var row : matrix) {
            for (var num : row) {
                System.out.printf("%4d", num);
            }
            System.out.println();
        }
    }
}
