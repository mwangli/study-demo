package mwang.online.hot100;

import java.util.Stack;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/8 15:32
 * @description: No155_MinStack
 */
public class No155_MinStack {

    Stack<Long> stack;
    long min;

    public static void main(String[] args) {
        final No155_MinStack stack = new No155_MinStack();
        stack.push(3);
        stack.push(2);
        stack.push(1);
        stack.pop();
        System.out.println(stack.getMin());
        System.out.println(stack.top());
    }

    public No155_MinStack() {
        this.stack = new Stack<>();
    }

    public void push(int val) {
        // 压入第一个元素
        if (stack.isEmpty()) {
            min = val;
            stack.push(0L);
        } else {
            // 压入当前值和上一个最小值的差
            stack.push((long) val - min);
            // 更新当前的最小值
            min = Math.min(val, min);
        }

    }

    public void pop() {
        var pop = stack.pop();
        // 当弹出元素小于0时，说明弹出元素是当前栈中的最小值，要更新最小值
        if (pop < 0) {
            // min代表当前值 val - (val - lasMin) = lasMin
            var val = min;
            var lastMin = val- pop;
            min = lastMin;
        }
        // 说明当前值不是最小值
    }

    public int top() {
        long peek = stack.peek();
        // 若当前栈顶小于等于0，说明当前值就是最小值，而当前值存在min中
        if (peek <= 0) return (int) min;
        // 否则说明当前元素不是最小值，min代表上一个最小值
        // lastMin + (val - lastMin) = val
        return (int) (min + peek);
    }

    public int getMin() {
        return (int) min;
    }
}
