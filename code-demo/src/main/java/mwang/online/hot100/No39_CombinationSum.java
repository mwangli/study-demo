package mwang.online.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. 组合总和
 * 思路：递归回溯剪枝
 */
public class No39_CombinationSum {

    public static void main(String[] args) {
        int[] ints = {2, 3, 6, 7};
        System.out.println(combinationSum(ints, 7));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<List<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> res = new ArrayList<>();
        // 排序后方便剪枝
        Arrays.sort(candidates);
        dfs(candidates, target, 0, 0, ans, res);
        return ans;
    }

    public static void dfs(int[] candidates, int target, int sum, int index, List<List<Integer>> ans, List<Integer> path) {
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            int candidate = candidates[i];
            sum += candidate;
            // 因为有序，后面的数比当前值更大直接排除剪枝
            if (sum > target) break;
            path.add(candidate);
            // 可选范围缩小确保不重复
            dfs(candidates, target, sum, i, ans, path);
            // 撤销
            path.remove(path.lastIndexOf(candidate));
            sum -= candidate;
        }
    }
}
