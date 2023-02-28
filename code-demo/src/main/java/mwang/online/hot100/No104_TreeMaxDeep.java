package mwang.online.hot100;

import mwang.online.base.TreeNode;

import java.util.LinkedList;

/**
 * 104. 二叉树的最大深度
 * 思路：递归，最大深度为子树的最大深度+1
 */
public class No104_TreeMaxDeep {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3), null), null);
        System.out.println(maxDepth2(root));
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        final LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                final TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res++;
        }
        return res;
    }
}
