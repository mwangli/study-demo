package mwang.online.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 * https://leetcode.cn/problems/subsets/description/
 * 思路：递归回溯，对于每个元素，只有在子集中或者不在子集中两种状态，递归穷举即可
 * 时间复杂度 O(2^n)
 */
public class No76_SubSet {

    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4};
        List<List<Integer>> subsets = subsets(ints);
        System.out.println(subsets);
    }

    public static List<List<Integer>> subsets(int[] nums) {
        ArrayList<List<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        dfs(ans, temp, nums, 0);
        return ans;
    }

    public static void dfs(List<List<Integer>> res, List<Integer> tmp, int[] nums, int index) {
        // 递归遍历到最后一个元素，则加入集合中
        if (index == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        // 子集中包含该元素
        tmp.add(nums[index]);
        dfs(res, tmp, nums, index + 1);
        // 子集中不包含该元素
        tmp.remove(Integer.valueOf(nums[index]));
        dfs(res, tmp, nums, index + 1);
    }
}
