package mwang.online.hot100;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/4/28 09:06
 * @description: No461_HanMingDistance
 */
public class No461_HanMingDistance {

    public static void main(String[] args) {
        int x = 1;
        int y = 4;
        System.out.println(hammingDistance(x, y));
    }

    public static int hammingDistance(int x, int y) {
        System.out.println(Integer.toBinaryString(x));
        System.out.println(Integer.toBinaryString(y));
        final int z = x ^ y;
        System.out.println(Integer.toBinaryString(z));
        return Integer.bitCount(z);
    }
}
