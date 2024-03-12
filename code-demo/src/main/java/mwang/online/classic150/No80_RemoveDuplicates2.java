package mwang.online.classic150;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/3/11 09:49
 * @description: No88_RemoveDuplicates2
 */
@Slf4j
public class No80_RemoveDuplicates2 {

    public static void main(String[] args) {

        int[] nums = {1, 1, 1, 2, 2, 3, 3, 3};
        new No80_RemoveDuplicates2().removeDuplicates(nums);
        System.out.println(Arrays.toString(nums));
    }

    public int removeDuplicates(int[] nums) {
        int left = 0;
        int right = 1;
        int count = 1;
        while (right < nums.length) {
            // 当出现新的数字,交换到有效数组的后面
            // nums[left]代表有效数组的最后一位
            // nums[right]代表当前扫描到的数字
            if (nums[right] != nums[left]) {
                swap(nums, left + 1, right);
                left++;
                count = 1;
            } else {
                // 只出现1次的重复数字任然有效
                if (count < 2) {
                    swap(nums, left + 1, right);
                    left++;
                    count++;
                }
            }
            right++;
        }
        return left + 1;
    }

    private void swap(int[] nums, int left, int right) {
        int t = nums[left];
        nums[left] = nums[right];
        nums[right] = t;
    }
}
