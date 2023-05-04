package mwang.online.hot100;

import mwang.online.base.TreeNode;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/5/4 10:43
 * @description: No543_BinaryTreeDeepth
 */
public class No543_BinaryTreeDepth {

    private static int maxSum = 0;

    public static void main(String[] args) {

    }

    public int diameterOfBinaryTree(TreeNode root) {
        maxSum = 0;
        dfs(root);
        return maxSum;
    }

    public static int dfs(TreeNode root) {
        if (root == null) return 0;
        final int leftDepth = dfs(root.left);
        final int rightDepth = dfs(root.right);
        final int sum = leftDepth + rightDepth;
        maxSum = Math.max(maxSum, sum);
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
