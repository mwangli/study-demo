package mwang.online;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 20. 有效的括号 - easy
 * https://leetcode.cn/problems/valid-parentheses/description/
 */
public class BracketsValidate {

    public static void main(String[] args) {
        System.out.println(isValid("{[()]}"));
    }

    public static boolean isValid(String s) {
        Stack<Character> characterStack = new Stack<>();
        List<String> targets = Arrays.asList("()", "[]", "{}");
        // 遍历元素压入栈中，判断栈顶元素是否满足出栈条件
        for (int i = 0; i < s.length(); i++) {
            Character top = characterStack.isEmpty() ? ' ' : characterStack.peek();
            String str = top + String.valueOf(s.charAt(i));
            if (targets.contains(str)) {
                characterStack.pop();
            } else {
                characterStack.push(s.charAt(i));
            }
        }
        return characterStack.isEmpty();
    }
}
