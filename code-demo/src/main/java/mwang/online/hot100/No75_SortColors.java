package mwang.online.hot100;

import java.util.Arrays;

/**
 * 75. 颜色分类
 */
public class No75_SortColors {

    public static void main(String[] args) {
        int[] ints = {2,1,0,2,1,0};
        sortColors2(ints);
        System.out.println(Arrays.toString(ints));
    }

    public static void sortColors(int[] nums) {
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) count0++;
            if (nums[i] == 1) count1++;
            if (nums[i] == 2) count2++;
        }
        int i = 0;
        while (count0-- > 0) nums[i++] = 0;
        while (count1-- > 0) nums[i++] = 1;
        while (count2-- > 0) nums[i++] = 2;
    }


    public static void sortColors2(int[] nums) {
        // 代表数组0的长度
        int left = 0;
        // 表数据2的长度
        int right = nums.length - 1;
        for (int i = 0; i <= right; i++) {
            // 如果被交换过来的元素是2，则继续交换到末尾
            // 如果是0则交换到前面，如果是1则保留(保留的1最前面的部分会被后续的0交换掉)
            while (i <= right && nums[i] == 2) {
                swap(nums, i, right--);
            }
            if (nums[i] == 0)
                swap(nums, i, left++);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
