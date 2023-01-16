package mwang.online;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 1. 两数之和 - e  asy
 * https://leetcode.cn/problems/two-sum/description/
 */
public class AddTwoNumber {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] res = twoSum2(nums, target);
        System.out.println(Arrays.toString(res));
    }

    public static int[] twoSum(int[] source, int target) {
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source.length; j++) {
                if (i != j && source[i] + source[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static int[] twoSum2(int[] source, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < source.length; i++) {
            int j = target - source[i];
            if (map.containsKey(j)) {
                return new int[]{i, map.get(j)};
            } else {
                map.put(source[i],i);
            }
        }
        return null;
    }
}
