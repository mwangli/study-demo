package mwang.online.hot100;

import mwang.online.base.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class No94_TreeSearch {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        System.out.println(inorderTraversal(root));
    }


    public static List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        middleSearch2(root, res);
        return res;
    }

    public static void middleSearch(TreeNode root, List<Integer> res) {
        if (root == null) return;
        if (root.left != null) {
            middleSearch(root.left, res);
        }
        res.add(root.val);
        if (root.right != null) {
            middleSearch(root.right, res);
        }
    }

    public static void middleSearch2(TreeNode root, List<Integer> res) {
        if (root == null) return;
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        while (cur != null || !stack.isEmpty()) {
            // 先找到最左节点
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }
    }

}
