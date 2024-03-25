package mwang.online.classic150;

import java.util.Arrays;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/3/12 09:40
 * @description: No189_RotateArray
 */
public class No189_RotateArray {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};
        int k = 2;
        new No189_RotateArray().rotate(nums, k);
        System.out.println(Arrays.toString(nums));
    }

    // 方法一：用一个新数组来接收
    // 时间复杂度: O(n), 空间复杂度:O(n)
    public void rotate1(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        int[] newNums = new int[n];
        for (int i = 0; i < n; i++) {
            newNums[i] = nums[(i + (n - k)) % n];
        }
        System.arraycopy(newNums, 0, nums, 0, n);
    }

    // 方法3，数组翻转 时间复杂度 O(n), 空间复杂度O(1)
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverseArray(nums, 0, nums.length - 1);
        reverseArray(nums, 0, k - 1);
        reverseArray(nums, k, nums.length - 1);
    }

    private void reverseArray(int[] nums, int start, int end) {
        while (start < end) {
            int t = nums[start];
            nums[start] = nums[end];
            nums[end] = t;
            start++;
            end--;
        }
    }
}
