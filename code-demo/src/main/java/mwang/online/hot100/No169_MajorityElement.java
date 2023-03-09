package mwang.online.hot100;

import java.util.HashMap;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/9 09:06
 * @description: No169_MajorityElement
 * 169. 多数元素
 */
public class No169_MajorityElement {

    public static void main(String[] args) {
        final int[] ints = {1, 2, 2};
        System.out.println(majorityElement(ints));
    }

    public static int majorityElement(int[] nums) {
        final HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (var entry : map.entrySet()) {
            if (entry.getValue() > nums.length / 2) {
                return entry.getKey();
            }
        }
        return 0;
    }


    public static int majorityElement2(int[] nums) {
        int major = nums[0];
        int count = 0;
        for (int num : nums) {
            // 如果相等则投票加一
            if (num == major) {
                count++;
            } else if (count == 0) {
                // 如果没有候选，则投选自己
                major = num;
            } else {
                // 如果不相等，则反对投票
                count--;
            }
        }
        return major;
    }

    public static int majorityElement3(int[] nums) {
        int major = nums[0];
        int count = 0;
        for (int num : nums) {
            // 如果没有候选
            if (count == 0) {
                major = num;
            }
            // 相同投票加1，否则减1
            count += num == major ? 1 : -1;
        }
        return major;
    }
}
