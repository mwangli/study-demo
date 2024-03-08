package mwang.online.top200;

import java.util.Arrays;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/3/8 10:35
 * @description: No26_RemoveDuplicates
 */
public class No26_RemoveDuplicates {

    public static void main(String[] args) {
        int[] nums = {1};
        final int res = new No26_RemoveDuplicates().removeDuplicates(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println(res);
    }


    // 要求保留顺序,将不重复的数值移动到左边
    // (left,right]为重复数值
    public int removeDuplicates(int[] nums) {
        int left = 0;
        int right = 1;
        while (right < nums.length) {
            if (nums[left] != nums[right]) {
                nums[left + 1] = nums[right];
                left++;
            }
            right++;
        }
        return left + 1;
    }
}
