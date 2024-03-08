package mwang.online.top200;

import java.util.Arrays;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/3/7 10:16
 * @description: No88_MergeTwoArray
 */
public class No88_MergeTwoArray {


    public static void main(String[] args) {
//        int[] nums1 = {2, 0};
//        int[] nums2 = {1};
//        int m = 1, n = 1;
//
//
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        int m = 3, n = 3;

//        int[] nums1 = {0, 0, 0};
//        int[] nums2 = {1, 2, 3};
//        int m = 0, n = 3;
        new No88_MergeTwoArray().merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }

    // 将数组2中的元素按顺序插入数组1中
    // 逐一比较数组2和数组1中的数值大小，直到合适的位置插入，再后移其他元素
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 数组2的查找索引
        int index = 0;
        // 数组1的最大索引
        int left = 0;
        int right = m - 1;
        // 如果nums1数组为空，则直接插入第一个元素
        if (right < 0) {
            nums1[0] = nums2[index];
            right++;
            nums2[index] = 0;
            index++;
        }
        while (index < n && left <= right) {
            if (nums2[index] > nums1[left]) {
                // 如果大于，则继续往后查找
                left++;
            } else {
                // 从后往前挨个移动元素
                for (int j = nums1.length - 1; j > left; j--) {
                    nums1[j] = nums1[j - 1];
                }
                // 插入到当前位置
                nums1[left] = nums2[index];
                nums2[index] = 0;
                right++;
                index++;
                left++;
            }
            // 如果到末尾还没查找到，将元素插入到末尾
            if (left > right) {
                nums1[left] = nums2[index];
                nums2[index] = 0;
                right++;
                index++;
            }
        }
    }
}
