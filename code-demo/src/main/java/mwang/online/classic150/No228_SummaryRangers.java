package mwang.online.classic150;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/3/8 14:22
 * @description: No228_SummaryRangers
 */
public class No228_SummaryRangers {

    public static void main(String[] args) {
        final int[] ints = {0, 2, 4, 5, 7};
//        final List<String> res = new No228_SummaryRangers().summaryRanges(ints);
        final List<String> res = new No228_SummaryRangers().summaryRanges2(ints);
        System.out.println(res);
    }

    public List<String> summaryRanges(int[] nums) {
        final ArrayList<String> res = new ArrayList<>();
        // [left,right]表示一个区间
        // 当遇到不连续的数值，则表示遇到一个完整的区间
        int left = 0, right = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 >= nums.length || nums[i] + 1 != nums[i + 1]) {
                // 找到完整区间
                if (left == right) {
                    // 单值区间
                    res.add(String.valueOf(nums[left]));
                } else {
                    // 多值区间
                    res.add(String.valueOf(nums[left]).concat("->").concat(String.valueOf(nums[right])));
                }
                // 更新区间起点
                left = right + 1;
            }
            right++;
        }
        return res;
    }


    // 输出连续数字的头尾
    public List<String> summaryRanges2(int[] nums) {
        final ArrayList<String> res = new ArrayList<>();
        final Stack<Integer> stack = new Stack<>();
        for (int num : nums) {
            if (!stack.isEmpty() && !stack.peek().equals(num - 1)) {
                // 如果栈为空，直接入栈
                // 如果不为空，则判断是否连续
                // 连续则入栈，不连续先出栈后入栈
                clearStack(stack, res);
            }
            stack.push(num);
        }
        clearStack(stack, res);
        return res;
    }

    private void clearStack(Stack<Integer> stack, List<String> res) {
        if (stack.isEmpty()) return;
        if (stack.size() == 1) {
            res.add(stack.pop().toString());
        } else {
            Integer last = stack.pop();
            Integer first = stack.pop();
            while (!stack.isEmpty()) {
                first = stack.pop();
            }
            res.add(first.toString().concat("->").concat(last.toString()));
        }
    }
}
