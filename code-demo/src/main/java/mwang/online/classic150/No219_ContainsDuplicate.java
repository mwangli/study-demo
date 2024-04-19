package mwang.online.classic150;

import org.junit.Test;

import java.util.HashSet;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/4/19 09:29
 * @description: No219_ContainsDuplicate
 */
public class No219_ContainsDuplicate {


    @Test
    public void test() {

        int[] nums1 = {1, 2, 3, 1};
        int k1 = 3;
        assert containsNearbyDuplicate(nums1, k1);
        int[] nums2 = {1, 2, 3, 1, 2, 3};
        int k2 = 2;
        assert !containsNearbyDuplicate(nums2, k2);
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int left = 0, right = 0;
        HashSet<Integer> set = new HashSet<>();
        while (right < nums.length) {
            if (set.contains(nums[right])) {
                return true;
            } else {
                set.add(nums[right]);
            }
            right++;
            if (right - left > k) {
                set.remove(nums[left]);
                left++;
            }
        }
        return false;
    }
}
