package mwang.online.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列
 * 思路：递归回溯剪枝
 */
public class No46_NumsPermutation {

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 3}));
    }

    public static List<List<Integer>> permute(int[] nums) {
        ArrayList<List<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        dfs(nums, 0, temp, ans);
        return ans;
    }

    public static void dfs(int[] nums, int index, List<Integer> temp, List<List<Integer>> ans) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        for (var num : nums) {
            if (temp.contains(num)) continue;
            temp.add(num);
            dfs(nums, index + 1, temp, ans);
            temp.remove(index);
        }
    }
}
