package mwang.online.hot100;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/5/5 10:15
 * @description: No581_UnsortedSubArray
 */
public class No581_UnsortedSubArray {

    public static void main(String[] args) {

    }

    public static int findUnsortedSubarray(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int n = nums.length;
        int left = -1, right = -1;
        for (int i = 0; i < n; i++) {
            // 从右往左找最小值
            if (nums[n - i - 1] <= min) {
                min = nums[n - i - 1];
            } else {
                left = n - i - 1;
            }
            // 从左往右找最大值
            if (nums[i] >= max) {
                max = nums[i];
            } else {
                right = i;
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }
}
