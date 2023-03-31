package mwang.online.hot100;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/31 10:08
 * @description: No279_NumSquares
 * 动态规划：f(i) 表示和为 i的所需最小完全平方数 数量
 * 转移方程: f(i) = 1 + min{ f(i - j^2) }
 * 数学定理：四平方定理，任何一个正整数可以被至多四个完全平方数来表示
 * 进阶版：当n= k^k(8m+7)时，只能被四个完全平方数表示
 * 否则，可以被至多3个表示
 */
public class No279_NumSquares {

    private static int MAX = 10000;
    private static int[] dp = new int[MAX + 1];
    private static int[] squares = new int[MAX + 1];

    static {
        for (int i = 1; i <= MAX; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, dp[i - j * j]);
            }
            dp[i] = 1 + min;
        }
    }

    static {
        for (int i = 0; i < 100; i++) {
            squares[i * i] = 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(numSquares2(12));
    }

    public static int numSquares(int n) {
        return dp[n];
    }

    public static int numSquares2(int n) {
        if (is4k8m7(n)) return 4;
        else {
            if (isSquare(n)) return 1;
            // 包含两个解时 a^2 + b^2 = n;枚举
            for (int i = 0; i * i <= n; i++) {
                if (isSquare(i * i) && isSquare(n - i * i))
                    return 2;
            }
            return 3;
        }
    }

    public static boolean isSquare(int n) {
        return squares[n] == 1;
    }

    public static boolean is4k8m7(int n) {
        while (n % 4 == 0) n /= 4;
        return n % 8 == 7;
    }
}
