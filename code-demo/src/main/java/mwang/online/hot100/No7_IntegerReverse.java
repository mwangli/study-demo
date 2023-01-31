package mwang.online.hot100;

/**
 * 7. 整数反转 - middle
 * https://leetcode.cn/problems/reverse-integer/description/
 * 思路：除10取余截取低位，再乘10加余数推上高位
 */
public class No7_IntegerReverse {

    public static void main(String[] args) {
        System.out.println(reverse2(-2147483648));
    }

    public static int reverse(int x) {
        // 正负数处理
        int base = 1;
        if (x < 0) {
            base = -1;
        }
        // 截取末位
        StringBuilder builder = new StringBuilder();
        while (x != 0) {
            int i = x % 10;
            builder.append(i < 0 ? -i : i);
            x /= 10;
        }
        // 计算数值
        int length = builder.length();
        String res = builder.toString();
        long sum = 0;
        for (int i = 0; i < res.length(); i++) {
            int s = Integer.parseInt(String.valueOf(res.charAt(i)));
            sum += s * Math.pow(10, length - i - 1);
        }
        sum *= base;
        if (sum > Integer.MAX_VALUE || sum < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) sum;
    }

    public static int reverse2(int x) {
        long sum = 0;
        while (x != 0) {
            int i = x % 10;
            sum = sum * 10 + i;
            x /= 10;
        }
        return (int) sum == sum ? (int) sum : 0;
    }
}
