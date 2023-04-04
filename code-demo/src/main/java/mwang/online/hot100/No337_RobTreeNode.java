package mwang.online.hot100;

import mwang.online.base.TreeNode;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/4/4 10:09
 * @description: No337_RobTreeNode
 */
public class No337_RobTreeNode {

    public static void main(String[] args) {
        final TreeNode root = new TreeNode(3, new TreeNode(2, null, new TreeNode(3)),
                new TreeNode(3, null, new TreeNode(1)));
        System.out.println(rob(root));
    }

    public static int rob(TreeNode root) {
        final int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }

    public static int[] dfs(TreeNode root) {
        // 第一个数表示包含当前节点，第二个数表示不包含当前节点
        if (root == null) return new int[2];
        final int[] left = dfs(root.left);
        final int[] right = dfs(root.right);
        // 包含当前节点则不能包含子节点
        int v1 = root.val + left[1] + right[1];
        // 不包含当前节点，则子节点可以自由选择比较出较大值(分别选出左右子树的最大值)
        int v2 = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{v1, v2};
    }
}
