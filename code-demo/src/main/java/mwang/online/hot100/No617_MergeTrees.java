package mwang.online.hot100;

import mwang.online.base.TreeNode;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/5/5 10:24
 * @description: No617_MergeTrees
 */
public class No617_MergeTrees {

    public static void main(String[] args) {
        final TreeNode node1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        final TreeNode node2 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(mergeTrees(node1, node2));
    }

    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        root1.val= root1.val+root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }

}
