package mwang.online.hot100;

import java.util.HashSet;

/**
 * 62. 不同路径
 * https://leetcode.cn/problems/unique-paths/description/
 * 思路：1.递归回溯 2.排列组合
 */
public class No62_UniquePaths {

    public static void main(String[] args) {
        long res = new No62_UniquePaths().uniquePaths2(10, 10);
        System.out.println(res);
    }

    /**
     * 当 m = n = 3 时，res = C4^2
     * m = 4 ,n =2 时, res = c4^1
     * 令 x = m-1 ,y = n-1;
     * paths(m,n) => res = C(x+y)^x = C(x+y)^y
     * 排列组合公式
     */
    public int uniquePaths2(int m, int n) {
        var x = m - 1;
        var y = n - 1;
        var min = Math.min(x, y);
        var max = x + y;
        // 计算排列公式 C(max,min)x
        long sum = 1;
        for (int i = max, j = 1; j <= min; i--, j++) {
            sum = sum * i / j;
        }
        return (int) sum;
    }

    public int uniquePaths(int m, int n) {
        HashSet<String> res = new HashSet<>();
        dfs(m, n, 0, 0, new StringBuilder(), res);
        System.out.println(res);
        return res.size();
    }

    public void dfs(int m, int n, int x, int y, StringBuilder path, HashSet<String> resultSet) {
        // 递归终止件
        if (x == n - 1 && y == m - 1) {
            resultSet.add(new String(path));
            return;
        }
        // 说明可以向右走
        if (x < n) {
            path.append("R");
            dfs(m, n, x + 1, y, path, resultSet);
            path.deleteCharAt(path.lastIndexOf("R"));
        }
        //  说明可以先向下走
        if (y < m) {
            path.append("D");
            dfs(m, n, x, y + 1, path, resultSet);
            path.deleteCharAt(path.lastIndexOf("D"));
        }
    }
}
