package mwang.online;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class MedianStoredNumber {

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
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
