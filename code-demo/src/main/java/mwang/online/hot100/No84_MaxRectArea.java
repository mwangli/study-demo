package mwang.online.hot100;

import java.util.Arrays;
import java.util.Stack;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/5/11 15:48
 * @description: No84_MaxRectArea
 */
public class No84_MaxRectArea {

    public static void main(String[] args) {
        final int[] ints = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(ints));
        System.out.println(largestRectangleArea2(ints));
        System.out.println(largestRectangleArea3(ints));
    }

    public static int largestRectangleArea(int[] heights) {
        // 枚举宽度
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int minHeight = heights[i];
            for (int j = i; j < heights.length; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                int area = minHeight * (j - i + 1);
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    public static int largestRectangleArea2(int[] heights) {
        // 枚举高度，向左右扩展
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int height = heights[i];
            int left = i;
            int right = i;
            while (left - 1 >= 0 && heights[left - 1] >= heights[i]) left--;
            while (right + 1 < heights.length && heights[right + 1] >= heights[i]) right++;
            int area = (right - left + 1) * heights[i];
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    public static int largestRectangleArea3(int[] heights) {
        // 单调栈
        final Stack<Integer> stack = new Stack<>();
        final int[] left = new int[heights.length];
        final int[] right = new int[heights.length];
        Arrays.fill(right, heights.length);
        for (int i = 0; i < heights.length; i++) {
            int height = heights[i];
            while (!stack.isEmpty() && height < heights[stack.peek()]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        System.out.println(Arrays.toString(left));

        System.out.println(Arrays.toString(right));
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int area = (right[i]-left[i]-1)*heights[i];
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
}
