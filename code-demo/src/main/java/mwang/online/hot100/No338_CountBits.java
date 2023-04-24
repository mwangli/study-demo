package mwang.online.hot100;

import java.util.Arrays;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/4/24 09:12
 * @description: No338_CountBits
 * 比特位计算
 * 1. bitCount内置函数
 * 2. 按位与计算
 * 3. 动态规划 f(i) = f(i-j) + 1; j<i, j是i的最高有效位
 */
public class No338_CountBits {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(countBits3(235)));
    }


    public static int[] countBits(int n) {
        final int[] res = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            final int bitCount = Integer.bitCount(i);
            res[i] = bitCount;
        }
        return res;
    }

    public static int[] countBits2(int n) {
        final int[] res = new int[n + 1];
        for (int i = 1; i <= n; i++) {
//            final int bitCount = Integer.bitCount(i);
            res[i] = countOne(i);

        }
        return res;
    }

    public static int[] countBits3(int n) {
        final int[] res = new int[n + 1];
        int highBit = 0;
        for (int i = 1; i <= n; i++) {
//            System.out.println(Integer.toBinaryString(i));
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            System.out.println(highBit);
            res[i] = res[i - highBit] + 1;

        }
        return res;
    }

    public static int countOne(int i) {
        int ones = 0;
        while (i > 0) {
            System.out.println(Integer.toBinaryString(i));
            System.out.println(Integer.toBinaryString(i - 1));
            i &= (i - 1);
            System.out.println(Integer.toBinaryString(i));
            System.out.println();
            ones++;
        }
        return ones;
    }
}
