package mwang.online.hot100;

import mwang.online.base.TreeNode;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/5/4 10:02
 * @description: No538_ConvertBST
 */
public class No538_ConvertBST {

    private static int sum = 0;

    public static void main(String[] args) {
        final TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        final TreeNode node = convertBST(root);
        System.out.println(node);
    }

    public static TreeNode convertBST(TreeNode root) {
        sum = 0;
        inorder(root);
        return root;
    }

    public static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.right);
        final int val = root.val;
        sum += val;
        root.val = sum;
        inorder(root.left);
    }
}
