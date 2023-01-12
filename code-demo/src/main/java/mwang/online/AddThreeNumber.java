package mwang.online;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 15. 三数之和 - middle
 * https://leetcode.cn/problems/3sum/description/
 */
public class AddThreeNumber {
    public static void main(String[] args) {
        System.out.println(threeSum2(new int[]{0, 0, 0, 0}));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                for (int k = 0; k < nums.length; k++) {
                    if (i != j && i != k && j != k && nums[i] + nums[j] + nums[k] == 0) {
                        int[] ints = {nums[i], nums[j], nums[k]};
                        Arrays.sort(ints);
                        String key = "" + ints[0] + ints[1] + ints[2];
                        if (!set.contains(key)) {
                            set.add(key);
                            result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        }
                    }
                }
            }
        }
        return result;
    }

    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        // 穷举第一个元素的位置
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 过滤第一个重复的元素，i>0防止i-1界越界
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            // 从左右往中间穷举第二三个元素的位置
            int k = n - 1;
            for (int j = i + 1; j < nums.length; j++) {
                // 过滤第二个重复的元素，j>i+1防止越界
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                //  从左右两侧往中间搜索
                while (j < k && nums[i] + nums[j] + nums[k] > 0) k--;
                // 找到第三个元素一次，无需再搜索其他重复值
                // 当第二三个位置重合，结束次轮搜索
                if (j == k) break;
                if (nums[i] + nums[j] + nums[k] == 0) {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                }
            }
        }
        return result;
    }
}
