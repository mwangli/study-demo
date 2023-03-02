package mwang.online.hot100;

import mwang.online.base.TreeNode;

import java.util.*;

/**
 * 114. 二叉树展开为链表
 * 1.遍历添加到链表 2.原地修改
 */
public class No114_TreeFlatten {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), new TreeNode(5, null, new TreeNode(6)));
        flatten(root);
    }

    public static void flatten(TreeNode root) {
        ArrayList<TreeNode> list = new ArrayList<>();
        preOrder2(root, list);
        for (int i = 0; i < list.size() - 1; i++) {
            TreeNode cur = list.get(i);
            cur.left = null;
            cur.right = list.get(i + 1);
        }
    }


    public static void preOrder(TreeNode root, List<TreeNode> list) {
        if (root == null) return;
        list.add(root);
        if (root.left != null) {
            preOrder(root.left, list);
        }
        if (root.right != null) {
            preOrder(root.right, list);
        }
    }


    public static void preOrder2(TreeNode root, List<TreeNode> list) {
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                list.add(root);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
    }
}
