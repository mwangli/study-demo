package mwang.online.hot100;

import mwang.online.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/23 09:47
 * @description: No226_InvertTree
 */
public class No226_InvertTree {

    public static void main(String[] args) {
        final TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        final TreeNode tree = invertTree2(root);
        System.out.println(tree);
    }


    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = temp;
        return root;
    }

    public static TreeNode invertTree2(TreeNode root) {
        final Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            final TreeNode node = queue.poll();
            final TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return root;
    }
}
