package mwang.online.hot100;

import mwang.online.base.TreeNode;

import java.util.HashMap;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/5/11 13:21
 * @description: No105_BuildTree
 */
public class No105_BuildTree {

    public static void main(String[] args) {
        final int[] preorder = {3, 9, 20, 15, 7};
        final int[] inorder = {9, 3, 15, 20, 7};
        System.out.println(buildTree(preorder, inorder));
    }

    static HashMap<Integer, Integer> map;

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        // 保存数值到索引的映射，因为数值不重复
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        int n = inorder.length - 1;
        return buildTree(preorder, 0, n, 0);
    }

    public static TreeNode buildTree(int[] preorder, int left, int right, int left1) {
        if (left > right) return null;
        final int rootVal = preorder[left];
        final TreeNode root = new TreeNode(rootVal);
        final Integer rootIndex = map.get(rootVal);
        final int leftSize = rootIndex - left1;
        root.left = buildTree(preorder, left + 1, left + leftSize, left1);
        root.right = buildTree(preorder, left + leftSize + 1, right, rootIndex + 1);
        return root;
    }
}
