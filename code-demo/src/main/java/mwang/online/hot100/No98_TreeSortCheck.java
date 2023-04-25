package mwang.online.hot100;

import mwang.online.base.TreeNode;

import java.util.Stack;

/**
 * 98. 验证二叉搜索树
 * Binary Sort Tree
 * 思路：递归遍历有序
 */
public class No98_TreeSortCheck {


    private static final boolean flag = true;

    public static void main(String[] args) {
//        final TreeNode root = new TreeNode(5, new TreeNode(1), new TreeNode(4, new TreeNode(3), new TreeNode(6)));
        final TreeNode root = new TreeNode(0, new TreeNode(0), new TreeNode(0));
        System.out.println(isValidBST(root));
        System.out.println(isValidBST2(root,Long.MIN_VALUE,Long.MAX_VALUE));
    }

    public static boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        long preValue = Long.MIN_VALUE;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= preValue) {
                return false;
            }
            preValue = root.val;
            root = root.right;
        }
        return true;
    }

    public static boolean isValidBST2(TreeNode root, long left, long right) {
        if (root == null) {
            return true;
        }
        if (root.val <= left || root.val >= right) {
            return false;
        }
        return isValidBST2(root.left, left, root.val) && isValidBST2(root.right, root.val, right);
    }
}
