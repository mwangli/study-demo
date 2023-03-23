package mwang.online.hot100;

import mwang.online.base.TreeNode;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/23 09:47
 * @description: No226_InvertTree
 */
public class No226_InvertTree {

    public static void main(String[] args) {
        final TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        final TreeNode tree = invertTree(root);
        System.out.println(tree);
    }


    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = temp;
        return root;
    }
}
