package mwang.online.classic150;

import org.junit.Assert;
import org.junit.Test;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/3/21 10:01
 * @description: No167_TwoSumII
 */
public class No167_TwoSumII {

    @Test
    public void test() {
        {   // case1
            var case1 = new int[]{2, 7, 11, 15};
            var target1 = 9;
            var res1 = new int[]{1, 2};
            Assert.assertArrayEquals(twoSum(case1, target1), res1);
        }
        {   // case2
            var case2 = new int[]{2, 3, 4};
            var target2 = 6;
            var res2 = new int[]{1, 3};
            Assert.assertArrayEquals(twoSum(case2, target2), res2);
        }
    }


    // O(n^2)
    public int[] twoSum3(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (i != j && numbers[i] + numbers[j] == target) {
                    return new int[]{i + 1, j + 1};
                }
            }
        }
        return new int[]{-1, -1};
    }

    // O(N*logN)
    public int[] twoSum2(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            // binary search
            final int findNumber = target - numbers[i];
            // start from i+1, because the left position have been searched in outer cycle.
            int left = i + 1, right = numbers.length - 1;
            while (left <= right) {
                int midIndex = (left + right) / 2;
                if (findNumber > numbers[midIndex]) {
                    left = midIndex + 1;
                } else if (findNumber < numbers[midIndex]) {
                    right = midIndex - 1;
                } else {
                    // find target
                    if (i != midIndex) {
                        return new int[]{i + 1, midIndex + 1};
                    }
                }
            }
        }
        return new int[]{-1, -1};
    }

    // O(n)
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            final int current = numbers[left] + numbers[right];
            // if current sum is bigger than target, need a smaller total sum
            if (current > target) {
                right--;
            }
            // if current sum is smaller than target, need a bigger total sum
            else if (current < target) {
                left++;
            } else {
                // find target
                return new int[]{left + 1, right + 1};
            }
        }
        return new int[]{-1, -1};
    }
}
