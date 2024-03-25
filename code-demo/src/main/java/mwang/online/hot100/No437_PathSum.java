package mwang.online.hot100;

import mwang.online.base.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/4/27 09:22
 * @description: No437_PathSum
 */
public class No437_PathSum {

    public static void main(String[] args) {

    }

    public static int pathSum(TreeNode root, int target) {
        if (root == null) return 0;
        int res = dfs(root, target);
        res += pathSum(root.left, target);
        res += pathSum(root.right, target);
        return res;
    }

    private static int dfs(TreeNode root, long target) {
        if (root == null) return 0;
        int res = 0;
        if (target == root.val) res++;
        res += dfs(root.left, target - root.val);
        res += dfs(root.right, target - root.val);
        return res;
    }


    public static int pathSum2(TreeNode root, int targetSum) {
        Map<Long, Integer> prefix = new HashMap<Long, Integer>();
        prefix.put(0L, 1);
        return dfs2(root, prefix, 0, targetSum);
    }

    public static int dfs2(TreeNode root, Map<Long, Integer> prefix, long curr, int targetSum) {
        if (root == null) {
            return 0;
        }

        int ret = 0;
        curr += root.val;

        ret = prefix.getOrDefault(curr - targetSum, 0);
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        ret += dfs2(root.left, prefix, curr, targetSum);
        ret += dfs2(root.right, prefix, curr, targetSum);
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);

        return ret;
    }



}
