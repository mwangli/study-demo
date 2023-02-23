package mwang.online.hot100;

import java.util.Arrays;

/**
 * 31. 下一个排列 https://leetcode.cn/problems/next-permutation/description/
 * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
 * <p>
 * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列
 * 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 * <p>
 * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
 * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
 * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 * <p>
 * 必须 原地 修改，只允许使用额外常数空间。
 * <p>
 * 首先从后向前查找第一个顺序对 (i,i+1)，满足 a[i]<a[i+1] 这样「较小数」即为 a[i]。此时 [i+1,n) 必然是下降序列。
 * <p>
 * 如果找到了顺序对，那么在区间 [i+1,n) 中从后向前查找第一个元素 j 满足 a[i]<a[j]。这样「较大数」即为 a[j]。
 * <p>
 * 交换 a[i] 与 a[j]，此时可以证明区间 [i+1,n) 必为降序。
 * 我们可以直接使用双指针反转区间 [i+1,n) 使其变为升序，而无需对该区间进行排序。
 */
public class No31_NextPermutation {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextPermutation(new int[]{1, 2, 3})));
    }

    public static int[] nextPermutation(int[] nums) {
        // 先找到最小数a[i]
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            // 找到较小数a[j]
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            // 交换a[i]和a[j]
            swap(nums, i, j);
        }
        // 翻转[i+1,n)区间数据
        reverse(nums, i + 1);
        return nums;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
