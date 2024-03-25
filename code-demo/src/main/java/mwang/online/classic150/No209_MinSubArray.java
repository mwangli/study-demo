package mwang.online.classic150;

import org.junit.Assert;
import org.junit.Test;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/3/22 09:48
 * @description: No209_MinSubArray
 */
public class No209_MinSubArray {

    @Test
    public void test() {
        var case1 = new int[]{2, 3, 1, 2, 4, 3};
        var target1 = 7;
        var res1 = 2;
        Assert.assertEquals(minSubArrayLen(target1, case1), res1);
        var case2 = new int[]{1, 4, 4};
        var target2 = 4;
        var res2 = 1;
        Assert.assertEquals(minSubArrayLen(target2, case2), res2);
        var case3 = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
        var target3 = 11;
        var res3 = 0;
        Assert.assertEquals(minSubArrayLen(target3, case3), res3);
        var case4 = new int[]{12, 28, 83, 4, 25, 26, 25, 2, 25, 25, 25, 12};
        var target4 = 213;
        var res4 = 8;
        Assert.assertEquals(minSubArrayLen(target4, case4), res4);
    }

    // O(n*2) O(1)
    public int minSubArrayLen3(int target, int[] nums) {
        int n = nums.length;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum >= target) {
                    int length = j - i + 1;
                    res = Math.min(res, length);
                    break;
                }
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    // binarySearch O(nlogn) O(n)
    public int minSubArrayLen2(int target, int[] nums) {
        int[] preSum = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // preSum[i] contains nums[i]
            preSum[i] = sum;
        }
        int res = Integer.MAX_VALUE;
        // preSum is sorted, so can use binary search
        for (int i = 0; i < nums.length; i++) {
            // search the right position
            // searchTarget - preSum[i] + num[i] = target
            final int searchTarget = target + preSum[i] - nums[i];
            int left = i, right = nums.length - 1;
            // can not search the target, skip this current
            if (searchTarget > preSum[right]) continue;
            while (left < right) {
                int midIndex = (left + right) / 2;
                if (searchTarget > preSum[midIndex]) {
                    left = midIndex + 1;
                } else if (searchTarget < preSum[midIndex]) {
                    // maybe the target is not present, so midIndex can not be right-1
                    right = midIndex;
                } else {
                    // do not have conflicts with upper situation
                    right = midIndex;
                }
            }
            //
            int length = left - i + 1;
            res = Math.min(res, length);
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    // slide windows O(n) O(1)
    // use slide windows to avoid repeated sum commute
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        // right++ to find target
        while (right < nums.length) {
            sum += nums[right];
            while (sum >= target) {
                int length = right - left + 1;
                res = Math.min(res, length);
                sum -= nums[left];
                // left++ is try to find the best answer
                left++;
            }
            right++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
