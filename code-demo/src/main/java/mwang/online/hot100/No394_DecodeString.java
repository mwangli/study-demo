package mwang.online.hot100;

import java.util.Stack;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/4/25 09:14
 * @description: No394_DecodeString
 */
public class No394_DecodeString {

    public static void main(String[] args) {
        String str = "3[a]2[bc]";
        System.out.println(decodeString(str));
//        System.out.println(decode("2[abc]"));
    }

    public static String decodeString(String s) {
        final Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            stack.push(c);
            // 检查是否满足弹出条件
            if (stack.peek() == ']') {
                // 弹出计算部分
                StringBuilder res = new StringBuilder();
                while (stack.peek() != '[')
                    res.append(stack.pop());
                // 弹出"["
                res.append(stack.pop());
                while (!stack.isEmpty() && stack.peek() >= '0' && stack.peek() <= '9')
                    res.append(stack.pop());
                // 将计算结果再次压入栈中
                final String decode = decode(res.reverse().toString());
                for (int j = 0; j < decode.length(); j++) {
                    stack.push(decode.charAt(j));
                }
            }
        }
        final StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) res.append(stack.pop());
        return res.reverse().toString();
    }

    private static String decode(String str) {
        final String[] split = str.split("\\[");
        int count = Integer.parseInt(split[0]);
        final String chars = split[1].split("]")[0];
        final StringBuilder stringBuilder = new StringBuilder();
        while (count-- > 0) stringBuilder.append(chars);
        return stringBuilder.toString();
    }
}
