package mwang.online.classic200;

import java.util.Arrays;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/3/7 16:07
 * @description: No27_RemoveArrayElement
 */
public class No27_RemoveArrayElement {


    public static void main(String[] args) {
//        int[] nums = {3,3};
        int[] nums = {3,2,2,3};
//        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
//        int val = 1;
        int val = 3;
        final int res = new No27_RemoveArrayElement().removeElement(nums, val);
        System.out.println(Arrays.toString(nums));
        System.out.println(res);
    }

    // 单指针法, 用一个指针来维护有效数组和无效数组的边界
    // [left,right) 表示有效数组区间
    public int removeElement(int[] nums, int val) {
        int left = 0;
        int right = nums.length -1;
        // 一次循环只移动一个指针
        while (left < right) {
            if (nums[left] == val) {
                int t = nums[left];
                nums[left] = nums[right];
                nums[right] = t;
                right--;
                // 左指针会再次判断，即便是重复元素
            } else {
                left++;
            }
        }
        return left;
    }
}
