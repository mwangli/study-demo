package mwang.online.hot100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/4/27 10:10
 * @description: No448_FindDisappearedNumbers
 */
public class No448_FindDisappearedNumbers {

    public static void main(String[] args) {
        final int[] ints = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(findDisappearedNumbers(ints));
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        final HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 1; i <= nums.length; i++) {
            hashSet.add(i);
        }
        for (int num : nums) {
            hashSet.remove(num);
        }
        return new ArrayList<>(hashSet);
    }

    public static List<Integer> findDisappearedNumbers2(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            final int index = nums[i];
            nums[index] = nums[i] % n + n;
        }
        final ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < n) {
                res.add(i + 1);
            }
        }
        return res;
    }
}
