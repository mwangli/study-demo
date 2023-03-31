package mwang.online.hot100;

import java.util.Arrays;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/31 14:06
 * @description: No283_MoveZeros
 */
public class No283_MoveZeros {

    public static void main(String[] args) {
        final int[] ints = {0, 1, 0, 3, 12};
        moveZeroes(ints);
        System.out.println(Arrays.toString(ints));
    }

    public static void moveZeroes(int[] nums) {
        int left = 0;
        for (int i = 0; i < nums.length; i++)
            if (nums[i] != 0)
                nums[left++] = nums[i];
        while (left < nums.length) nums[left++] = 0;
    }
}
