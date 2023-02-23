package mwang.online.daily;


import mwang.online.base.TreeNode;

/**
 * 2331. 计算布尔二叉树的值
 * 思路：二叉树的遍历(中序遍历+递归)
 */
public class No2331_BooleanTree {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3, new TreeNode(0), new TreeNode(1));
        boolean res = evaluateTree(root);
        System.out.println(res);
    }


    public static boolean evaluateTree(TreeNode root) {
        if (root.left != null && root.right != null) {
            boolean l = evaluateTree(root.left);
            boolean r = evaluateTree(root.right);
            return root.val == 2 ? l || r : l && r;
        }
        return root.val == 1;
    }
}
