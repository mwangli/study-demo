package mwang.online.hot100;

import java.util.Arrays;
import java.util.Stack;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/5/16 09:10
 * @description: No85_MaxMatrixArrea
 */
public class No85_MaxMatrixArea {

    public static void main(String[] args) {
        final char[][] matrix = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        System.out.println(maximalRectangle(matrix));
        System.out.println(maximalRectangle2(matrix));
    }

    public static int maximalRectangle(char[][] matrix) {
        final int r = matrix.length;
        final int l = matrix[0].length;
        final int[][] height = new int[r][l];
        // 统计连续为1的高度
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int index = j;
                while (index < matrix[0].length && matrix[i][index] == '1') index++;
                height[i][j] = index - j;
            }
        }
//        for (int i = 0; i < height.length; i++) {
//            System.out.println(Arrays.toString(height[i]));
//            System.out.println();
//        }
        // 以每一列为基准，求最大矩形面积
        int maxArea = 0;
        for (int j = 0; j < height[0].length; j++) {
            for (int i = 0; i < height.length; i++) {
                // 枚举高度h, 向两侧扩展
                final int h = height[i][j];
                int left = i;
                int right = i;
                while (left - 1 >= 0 && height[left - 1][j] >= h) left--;
                while (right + 1 < height.length && height[right + 1][j] >= h) right++;
                // 计算面积
                int area = h * (right - left + 1);
                maxArea = Math.max(area, maxArea);
            }
        }
        return maxArea;
    }

    public static int maximalRectangle2(char[][] matrix) {
        final int[][] height = new int[matrix.length][matrix[0].length];
        // 统计连续为1的高度
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int index = j;
                while (index < matrix[0].length && matrix[i][index] == '1') index++;
                height[i][j] = index - j;
            }
        }
//        for (int i = 0; i < height.length; i++) {
//            System.out.println(Arrays.toString(height[i]));
//            System.out.println();
//        }
        // 以每一列为基准，求最大矩形面积，使用单调栈
        int maxArea = 0;
        final Stack<Integer> stack = new Stack<>();
        final int[] left = new int[height.length];
        final int[] right = new int[height.length];
        for (int j = 0; j < height[0].length; j++) {
            stack.clear();
            Arrays.fill(left, 0);
            Arrays.fill(right, height.length);
            for (int i = 0; i < height.length; i++) {
                while (!stack.isEmpty() && height[i][j] < height[stack.peek()][j]) {
                    // 确定右边界，距离最近的较小值
                    right[stack.peek()] = i;
                    stack.pop();
                }
                // 确定左边界，距离最近的较小值
                left[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
            for (int i = 0; i < left.length; i++) {
                int area = height[i][j] * (right[i] - left[i] - 1);
                maxArea = Math.max(area, maxArea);
            }
        }
        return maxArea;
    }
}
