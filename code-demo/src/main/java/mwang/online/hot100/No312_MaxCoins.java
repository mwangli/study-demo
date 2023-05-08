package mwang.online.hot100;

import java.util.Arrays;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/5/8 15:47
 * @description: No312_MaxCoins
 */
public class No312_MaxCoins {

    static int[] val;
    static int[][] res;

    public static void main(String[] args) {
        final int[] ints = {3, 1, 5, 8};
        System.out.println(maxCoins2(ints));
    }

    public static int maxCoins(int[] nums) {
        // 构造边界值
        int n = nums.length;
        val = new int[n + 2];
        System.arraycopy(nums, 0, val, 1, n);
        val[0] = 1;
        val[n + 1] = 1;
        // 初始化区间
        res = new int[n + 2][n + 2];
        for (int[] re : res) {
            Arrays.fill(re, -1);
        }
        return solve(0, n + 1);
    }

    private static int solve(int left, int right) {
        if (left >= right - 1) return 0;
        if (res[left][right] != -1) return res[left][right];
        for (int i = left + 1; i < right; i++) {
            int sum = val[left] * val[i] * val[right];
            sum += solve(left, i) + solve(i, right);
            res[left][right] = Math.max(sum, res[left][right]);
        }
        return res[left][right];
    }

    public static int maxCoins2(int[] nums) {
        // 构造边界值
        int n = nums.length;
        val = new int[n + 2];
        System.arraycopy(nums, 0, val, 1, n);
        val[0] = 1;
        val[n + 1] = 1;
        // 初始化区间
        res = new int[n + 2][n + 2];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i+2; j <= n + 1; j++) {
                for (int k = i + 1; k < j; k++) {
                    int sum = val[i] * val[k] * val[j];
                    sum += res[i][k] + res[k][j];
                    res[i][j] = Math.max(res[i][j], sum);
                }
            }
        }
        return res[0][n + 1];
    }
}
