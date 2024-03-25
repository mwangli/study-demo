package mwang.online.hot100;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/4/3 09:25
 * @description: No287_FindDuplicate
 * 思路：
 * 1. 利用单调性进行二分查找
 * 2. 位处理
 * 3. 链表环判定算法
 */
public class No287_FindDuplicate {

    public static void main(String[] args) {

    }

    public static int findDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        int res = nums[0];
        while (left <= right) {
            int mid = (left + right) >> 1;
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }
            if (count <= mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
                res = mid;
            }
        }
        return res;
    }

    public static int findDuplicate2(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }
}
