package mwang.online.hot100;

/**
 * 53. 最大子数组和
 * 思路：1.暴力 2.分治
 */
public class No53_MaxSumOfSubArray {

    public static void main(String[] args) {
        int[] ints = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray2(ints));
    }

    public static int maxSubArray2(int[] nums) {
        int pre = 0;
        int maxValue = nums[0];
        for (var num : nums) {
            pre = Math.max(pre + num, num);
            maxValue = Math.max(maxValue, pre);
        }
        return maxValue;
    }

    public static int maxSubArray(int[] nums) {
        int maxValue = Integer.MIN_VALUE;
        for (var i = 0; i < nums.length; i++) {
            for (var j = i; j < nums.length; j++) {
                var sum = 0;
                var start = i;
                while (start <= j) {
                    sum += nums[start];
                    start++;
                }
                if (sum >= maxValue)
                    maxValue = sum;
            }
        }
        return maxValue;
    }
}
