package mwang.online.hot100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/5/9 14:51
 * @description: No301_RemovePattern
 * 思路：递归回溯+剪枝
 */
public class No301_RemovePattern {

    public static void main(String[] args) {
        System.out.println(removeInvalidParentheses("()())()"));
        System.out.println(removeInvalidParentheses2(")("));
    }

    public static List<String> removeInvalidParentheses2(String s) {
        // 去除多余的括号
        final ArrayList<String> res = new ArrayList<>();
        bfs(s, res);
        if (res.size() == 0) res.add("");
        return res;
    }

    public static List<String> removeInvalidParentheses(String s) {
        // 统计出多余的括号
        int countLeft = 0;
        int countRight = 0;
        for (int i = 0; i < s.length(); i++) {
            final char ch = s.charAt(i);
            if ('(' == ch) countLeft++;
            if (')' == ch) {
                if (countLeft == 0) countRight++;
                else countLeft--;
            }
        }
        // 去除多余的括号
        final ArrayList<String> res = new ArrayList<>();
        dfs(s, countLeft, countRight, 0, res);
        if (res.size() == 0) res.add("");
        return res;
    }

    public static void dfs(String str, int countLeft, int countRight, int start, ArrayList<String> res) {
        if (countLeft == 0 && countRight == 0 && check(str)) res.add(str);
        for (int i = start; i < str.length(); i++) {
            // 跳过重复的结果
            if (i != 0 && str.charAt(i) == str.charAt(i - 1)) continue;
            // 剩余长度不足
            if (countLeft + countRight > str.length() - i) return;
            final String subStr = str.substring(0, i) + str.substring(i + 1);
            if (countLeft > 0 && '(' == str.charAt(i))
                dfs(subStr, countLeft - 1, countRight, i, res);
            if (countRight > 0 && ')' == str.charAt(i))
                dfs(subStr, countLeft, countRight - 1, i, res);
        }
    }

    public static void bfs(String str, ArrayList<String> res) {
        HashSet<String> curSet = new HashSet<>();
        curSet.add(str);
        while (true) {
            for (String s : curSet) if (check(s)) res.add(s);
            if (res.size() > 0) return;
            final HashSet<String> nextSet = new HashSet<>();
            for (String s : curSet) {
                for (int i = 0; i < s.length(); i++) {
                    if (i != 0 && str.charAt(i) == str.charAt(i - 1)) continue;
                    if (')' == str.charAt(i) || '(' == str.charAt(i))
                        nextSet.add(str.substring(0, i) + str.substring(i + 1));
                }
            }
            curSet = nextSet;
        }
    }

    private static boolean check(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') count++;
            if (str.charAt(i) == ')') count--;
            if (count < 0) return false;
        }
        return count == 0;
    }
}
