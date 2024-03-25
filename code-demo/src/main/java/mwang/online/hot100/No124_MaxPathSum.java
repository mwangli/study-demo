package mwang.online.hot100;

import mwang.online.base.TreeNode;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/5/11 09:21
 * @description: No124_MaxPathSum
 */
public class No124_MaxPathSum {

    static Integer maxPathSum;

    public static void main(String[] args) {
        final TreeNode node = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(maxPathSum(node));
    }

    public static int maxPathSum(TreeNode root) {
        maxPathSum = -1;
        maxGain(root);
        return maxPathSum;
    }

    public static int maxGain(TreeNode root) {
        if (root == null) return 0;
        // 计算左右子节点的最大贡献值
        final int leftMax = Math.max(maxGain(root.left), 0);
        final int rightMax = Math.max(maxGain(root.right), 0);
        // 更新最大路径和
        final int pathSum = root.val + leftMax + rightMax;
        maxPathSum = Math.max(pathSum, maxPathSum);
        // 返回当前节点的最大贡献值
        return root.val + Math.max(leftMax, rightMax);
    }
}
