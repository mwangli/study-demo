package mwang.online.hot100;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 136. 只出现一次的数字
 */
public class No136_SingleNumber {

    public static void main(String[] args) {
        final int[] ints = {1, 2, 4, 2, 3, 4};
        System.out.println(singleNumber(ints));
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

        for (int num : nums) {
        }
        return 0;
    }
}
