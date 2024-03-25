package mwang.online.hot100;

import java.util.Arrays;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/29 10:17
 * @description: No238_ExpectSelf
 */
public class No238_ExpectSelf {

    public static void main(String[] args) {
        final int[] ints = {5, 6, 7, 8};
        final int[] res = productExceptSelf(ints);
        System.out.println(Arrays.toString(res));
    }

    public static int[] productExceptSelf2(int[] nums) {
        final int[] leftSum = new int[nums.length];
        leftSum[0] = 1;
        for (int i = 1; i < leftSum.length; i++) {
            leftSum[i] = leftSum[i - 1] * nums[i - 1];
        }
        final int[] rightSum = new int[nums.length];
        rightSum[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            rightSum[i] = rightSum[i + 1] * nums[i + 1];
        }
        final int[] res = new int[nums.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = leftSum[i] * rightSum[i];
        }
        return res;
    }

    public static int[] productExceptSelf(int[] nums) {
        // 先计算左侧乘积
        final int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < res.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        // 再计算右侧侧乘积
        int rightSum = 1;
        for (int i = res.length - 2; i >= 0; i--) {
            rightSum *= nums[i + 1];
            res[i] = res[i] * rightSum;
        }
        return res;
    }
}
