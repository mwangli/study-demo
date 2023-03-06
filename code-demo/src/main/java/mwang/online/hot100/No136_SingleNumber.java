package mwang.online.hot100;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 136. 只出现一次的数字
 * 位运算， 异或 ， a ^ a = 0(消消乐) a ^ 0 = a
 */
public class No136_SingleNumber {

    public static void main(String[] args) {
        final int[] ints = {1, 2, 2};
        System.out.println(singleNumber2(ints));
    }

    public static int singleNumber(int[] nums) {
        final HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        System.out.println(map);
        AtomicInteger res = new AtomicInteger();
        map.forEach((k, v) -> {
            if (v == 1) res.set(k);
        });
        return res.get();
    }

    public static int singleNumber2(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res = res ^ num;
        }
        return res;
    }
}
