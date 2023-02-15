package mwang.online.hot100;

/**
 * 70. 爬楼梯
 * 1.递归回溯(超时)
 * 2.动态规划，状态转移方程
 * F(i) = F(i-1) + F(i-2)
 * F(2) = 2
 * F(1) = 1
 */
public class No70_ClimbStairs {

    private static int count;

    private static final int[] res;

    static {
        res = new int[45 + 1];
        res[1] = 1;
        res[2] = 2;
        for (int i = 3; i < res.length; i++) {
            res[i] = res[i - 1] + res[i - 2];
        }
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(45));
        System.out.println(climbStairs2(45));
        System.out.println(climbStairs3(45));
    }

    public static int climbStairs(int n) {
        return res[n];
    }


    public static int climbStairs2(int n) {
        if (n == 2) return 2;
        if (n == 1) return 1;
        return climbStairs2(n - 1) + climbStairs2(n - 2);
    }

    public static int climbStairs3(int n) {
        count = 0;
        dfs(n, 0);
        return count;
    }

    public static void dfs(int n, int index) {
        if (index == n) {
            count++;
        }
        if (index + 1 <= n) {
            dfs(n, index + 1);
        }
        if (index + 2 <= n) {
            dfs(n, index + 2);
        }
    }
}
