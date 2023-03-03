package mwang.online.hot100;

import java.util.HashSet;

/**
 * 128. 最长连续序列
 */
public class No128_LongestConsecutive {

    public static void main(String[] args) {
        final int[] ints = {9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6};
        System.out.println(longestConsecutive(ints));
    }

    public static int longestConsecutive(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            var num = nums[i];
            hashSet.add(num);
        }
        int maxValue = 0;
        for (int i = 0; i < nums.length; i++) {
            var num = nums[i];
            if (!hashSet.contains(num - 1)) {
                int curValue = 1;
                while (hashSet.contains(num + 1)) {
                    curValue++;
                    num++;
                }
                maxValue = Math.max(maxValue, curValue);
            }
        }
        return maxValue;
    }
}
