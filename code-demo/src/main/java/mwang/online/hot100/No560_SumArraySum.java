package mwang.online.hot100;

import java.util.HashMap;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/5/4 11:04
 * @description: No560_SumArraySum
 */
public class No560_SumArraySum {

    public static void main(String[] args) {
        final int[] ints = {1, 2, 3};
        System.out.println(subarraySum2(ints, 3));
    }

    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }


    public static int subarraySum2(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        final HashMap<Integer, Integer> map = new HashMap<>();
        // 前面部分和为0
        map.put(0,1);
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
