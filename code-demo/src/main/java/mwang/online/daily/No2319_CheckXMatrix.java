package mwang.online.daily;

/**
 * 2319. 判断矩阵是否是一个 X 矩阵
 * X 矩阵 ：只有对角线元素为有效值(非0)
 * 思路：对角线元素行列值之和为固定值
 */
public class No2319_CheckXMatrix {

    public static void main(String[] args) {
        int[][] grid = {{2, 0, 0, 1}, {0, 3, 1, 0}, {0, 5, 2, 0}, {4, 0, 0, 2}};
        System.out.println(checkXMatrix(grid));
    }

    public static boolean checkXMatrix(int[][] grid) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
//                System.out.println(grid[i][j]);
                // 如果是对角线元素,则不能为0；如果是非对角线元素则必须为0
                if (i == j || i + j == n - 1) {
                    if (grid[i][j] == 0) return false;
                } else {
                    if (grid[i][j] != 0) return false;
                }
            }
        }
        return true;
    }
}
