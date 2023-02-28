package mwang.online.hot100;

import mwang.online.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 101. 对称二叉树
 * 思路：递归判断左子树和右子树是否对称
 */
public class No101_TreeSymmetric {

    public static void main(String[] args) {

    }

    public static boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    public static boolean check(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null && rightNode == null) {
            return true;
        }
        if (leftNode == null || rightNode == null) {
            return false;
        }
        return leftNode.val == rightNode.val && check(leftNode.left, rightNode.right) && check(leftNode.right, rightNode.left);
    }

    public static boolean check2(TreeNode leftNode, TreeNode rightNode) {
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(leftNode);
        nodeQueue.offer(rightNode);
        while (!nodeQueue.isEmpty()) {
            final TreeNode nod1 = nodeQueue.poll();
            final TreeNode node2 = nodeQueue.poll();
            if (nod1 == null && node2 == null) {
                continue;
            }
            if (nod1 == null || node2 == null) {
                return false;
            }
            if (nod1.val != node2.val) {
                return false;
            }
            nodeQueue.offer(leftNode.left);
            nodeQueue.offer(rightNode.right);
            // 两两对称相等
            nodeQueue.offer(leftNode.right);
            nodeQueue.offer(rightNode.left);
        }
        return true;
    }
}
