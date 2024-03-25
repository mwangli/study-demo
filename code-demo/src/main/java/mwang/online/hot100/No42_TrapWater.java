package mwang.online.hot100;

import java.util.Stack;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/5/19 10:13
 * @description: No42_TrapWater
 * 思路：
 * 1. 动态规划，找到左右边界最大值中的较小值，减去当前高度
 * 2. 单调递减栈栈，栈顶元素为左边界的最大值，当遇到较大值时，作为右边界计算
 */
public class No42_TrapWater {

    public static void main(String[] args) {
        final int[] ints = {4, 2, 1, 3, 0, 5};
        System.out.println(trap(ints));
        System.out.println(trap2(ints));
        System.out.println(trap3(ints));
    }

    public static int trap(int[] height) {
        final int[] left = new int[height.length];
        final int[] right = new int[height.length];
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > max) max = height[i];
            left[i] = max;
        }
        max = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            if (height[i] > max) max = height[i];
            right[i] = max;
        }
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            res += Math.min(left[i], right[i]) - height[i];
        }
//        System.out.println(Arrays.toString(height));
//        System.out.println(Arrays.toString(left));
//        System.out.println(Arrays.toString(right));
        return res;
    }

    public static int trap2(int[] height) {
        // 单调递减栈
        int res = 0;
        final Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                // 计算结果
                final Integer pop = stack.pop();
                if (stack.isEmpty()) break;
                // 左边界
                int left = stack.peek();
                final int h = Math.min(height[left], height[i]) - height[pop];
                final int l = i - left - 1;
                res += h * l;
            }
            stack.push(i);
        }
        return res;
    }

    public static int trap3(int[] height) {
        // 双指针
        int res = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax < rightMax) {
                res += leftMax - height[left];
                left++;
            } else {
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }
}
