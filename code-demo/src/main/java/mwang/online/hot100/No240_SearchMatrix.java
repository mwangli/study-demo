package mwang.online.hot100;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/30 14:34
 * @description: No240_SearchMatrix
 */
public class No240_SearchMatrix {

    public static void main(String[] args) {
//        int[][] ints = {
//                {1, 3, 5, 7, 9},
//                {2, 4, 6, 8, 10},
//                {11, 13, 15, 17, 19},
//                {12, 14, 16, 18, 20},
//                {21, 22, 23, 24, 25}};
        int[][] ints = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
//        int [][] ints = {{0}};
        System.out.println(searchMatrix3(ints, 21));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                if (target == anInt)
                    return true;
            }
        }
        return false;
    }

    public static boolean searchMatrix2(int[][] matrix, int target) {
        for (int[] ints : matrix) {
            int left = 0;
            int right = ints.length - 1;
            while (left <= right) {
                int middle = (right + left) / 2;
                if (target == ints[middle]) {
                    return true;
                } else if (target < ints[middle]) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }
        }
        return false;
    }

    public static boolean searchMatrix3(int[][] matrix, int target) {
        // 从右上角往左下角查找
        // 右上角原元素特征(所在行的最大值，所在列的最小值)
        // 对称地也可以从左下角往右上角查找
        int x = matrix.length - 1, y = 0;
        while (x >= 0 && y <= matrix[0].length - 1) {
            final int t = matrix[x][y];
            // 查找目标比当前行的最大值还要大，则排除当前行的所有元素
            if (target > t) y++;
                // 查找目标比当前列的最小值还要小，则排除当前列的所有元素
            else if (target < t) x--;
            else return true;
        }
        return false;
    }
}
