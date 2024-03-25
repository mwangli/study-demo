package mwang.online.hot100;

import java.util.Arrays;
import java.util.Stack;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/5/5 11:01
 * @description: No739_DailyTemperatures
 */
public class No739_DailyTemperatures {

    public static void main(String[] args) {
//        final int[] ints = {73, 74, 75, 71, 69, 72, 76, 73};
        final int[] ints = {3, 3, 4, 4, 5, 5};
        System.out.println(Arrays.toString(dailyTemperatures(ints)));
        System.out.println(Arrays.toString(dailyTemperatures2(ints)));
        System.out.println(Arrays.toString(dailyTemperatures3(ints)));
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        final int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[j] > temperatures[i]) {
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }

    public static int[] dailyTemperatures2(int[] temperatures) {
        final int[] res = new int[temperatures.length];
        // 保存每个温度值第一次出现的下标
        final int[] next = new int[101];
        Arrays.fill(next, Integer.MAX_VALUE);
        for (int i = temperatures.length - 1; i >= 0; i--) {
            // 从大于当前温度的数值中，找到下标最小值，即距离最近的更高温度
            int minIndex = Integer.MAX_VALUE;
            for (int j = temperatures[i] + 1; j <= 100; j++) {
                minIndex = Math.min(minIndex, next[j]);
            }
            // 更新更高温度距离
            if (minIndex != Integer.MAX_VALUE)
                res[i] = minIndex - i;
            // 保存下标备用
            next[temperatures[i]] = i;
        }
        return res;
    }

    public static int[] dailyTemperatures3(int[] temperatures) {
        final int[] res = new int[temperatures.length];
        // 单调栈存储下标，温度降序排列
        final Stack<Integer> stack = new Stack<>();
        // 遍历温度列表
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                // 出栈时更新结果
                final Integer index = stack.pop();
                res[index] = i - index;
            }
            stack.push(i);
        }
        return res;
    }
}
