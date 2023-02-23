package mwang.online.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成 - middle(递归回溯)
 * https://leetcode.cn/problems/generate-parentheses/description/
 * 思路：设计到穷举，且有撤销动作可使用递归回溯
 */
public class No22_BracketsGenerate {

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

    public static List<String> generateParenthesis(int n) {
        ArrayList<String> ans = new ArrayList<>();
        dfs(ans, new StringBuilder(), n, 0, 0, 0);
        return ans;
    }

    public static void dfs(List<String> ans, StringBuilder sb, int n, int openCount, int closeCount, int index) {
        if (index == 2 * n) {
            ans.add(sb.toString());
            return;
        }
        if (openCount < n) {
            sb.append("(");
            dfs(ans, sb, n, openCount + 1, closeCount, index + 1);
            sb.deleteCharAt(index);
        }
        if (closeCount < openCount) {
            sb.append(")");
            dfs(ans, sb, n, openCount, closeCount + 1, index + 1);
            sb.deleteCharAt(index);
        }
    }
}
