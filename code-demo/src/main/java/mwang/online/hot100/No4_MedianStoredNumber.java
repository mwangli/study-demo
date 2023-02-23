package mwang.online.hot100;

import java.util.Arrays;

/**
 * 4. 寻找两个正序数组的中位数 - hard
 * https://leetcode.cn/problems/median-of-two-sorted-arrays/description/
 */
public class No4_MedianStoredNumber {

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 合并两个数组再计算中位数
        int m = nums1.length;
        int n = nums2.length;
        int[] all = new int[m + n];
        System.arraycopy(nums1, 0, all, 0, m);
        System.arraycopy(nums2, 0, all, m, n);
        Arrays.sort(all);
        if ((m + n) % 2 == 1) {
            int k = (m + n) / 2;
            return all[k];
        } else {
            int k1 = (m + n) / 2;
            int k2 = k1 - 1;
            return Double.sum(all[k1], all[k2]) / 2;
        }
    }
}
