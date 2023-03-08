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
            // 栈不为空时，每次压入计算与min的差值后压入结果
            stack.push((long) val - min);
            // 更新min
            min = Math.min(val, min);
            // 上面两个语句是不能颠倒的！一定是先压入，在更新，因为min一定是当前栈中的最小值
        }

    }

    public void pop() {
        var pop = stack.pop();
        // 当弹出元素小于0时，说明弹出元素是当前栈中的最小值，要更新最小值
        if (pop < 0) {
            // 因为对于当前弹出的元素而言，计算压入栈中的值时，计算的是该元素与【未压入】该元素时
            // 栈中元素的最小值的差值，故弹出该元素后栈中的最小值就是未压入该元素时的最小值
            // 即当前元素的值（min）减去两者的差值
            var lastMin = min;
            min = (lastMin - pop);
        }
        // 若大于等于0，不会对min有影响
    }

    public int top() {
        long peek = stack.peek();
        // 若当前栈顶小于等于0，说明最小值就是栈顶元素
        if (peek <= 0) return (int) min;
        // 否则就是min+peek
        return (int) (min + peek);
    }

    public int getMin() {
        return (int) min;
    }
}
