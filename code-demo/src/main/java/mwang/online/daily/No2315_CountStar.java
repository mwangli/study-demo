package mwang.online.daily;

import java.util.Stack;

/**
 * 2315. 统计星号
 * 给你一个字符串 s ，每 两个 连续竖线 '|' 为 一对 。换言之，第一个和第二个 '|' 为一对，第三个和第四个 '|' 为一对，以此类推。
 * 请你返回 不在 竖线对之间，s 中 '*' 的数目。
 * 注意，每个竖线 '|' 都会 恰好 属于一个对。
 * 思路：成对字符可使用栈结构
 */
public class No2315_CountStar {

    public static void main(String[] args) {
        var count = new No2315_CountStar().countAsterisks("l|*e*et|c**o|*de|");
        System.out.println(count);
    }

    public int countAsterisks(String s) {
        // 使用栈结构来处理，遇到两个封闭的'|'，则弹出中间的内容，最后统计剩余‘*’的数量
        Stack<Character> stack = new Stack<>();
        // 用来标记上一个竖线的深度
        int deep = 0;
        // 标记是否含有竖线
        boolean flag = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 如果当前字符不是‘|’则直接压入栈中
            if (c != '|') {
                stack.push(c);
                // 如果栈中已经有一个竖线，则深度+1
                if (flag) {
                    deep++;
                }
            }
            // 如果当前字符是‘|’则需判断栈中是否有之前的竖线，如果没有则压入标记，
            else {
                //
                if (!flag) {
                    stack.push(c);
                    flag = true;
                } else {
                    // 如果有则弹出其中包含的内容并将deep和标记归零
                    while (deep-- >= 0) {
                        stack.pop();
                    }
                    deep = 0;
                    flag = false;
                }
            }
        }
        // 最后统计栈中*的数量
        int count = 0;
        while (!stack.isEmpty()) {
            Character ch = stack.pop();
            if (ch == '*') {
                count++;
            }
        }
        return count;
    }
}
