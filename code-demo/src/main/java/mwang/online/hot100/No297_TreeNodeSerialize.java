package mwang.online.hot100;

import mwang.online.base.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/5/9 16:53
 * @description: No297_TreeNodeSerialize
 */
public class No297_TreeNodeSerialize {

    public static void main(String[] args) {
        final TreeNode node = new TreeNode(1, new TreeNode(2, null, new TreeNode(4)), new TreeNode(3, null, new TreeNode(5)));
        final String serialize = serialize(node);
        System.out.println(serialize);
        System.out.println(deserialize(serialize, 0));


    }

    public static String serialize(TreeNode root) {
        final StringBuilder stringBuilder = new StringBuilder();
        dfs(root, stringBuilder);
        return stringBuilder.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data, int index) {
        final String[] split = data.split(",");
        return dfs(new ArrayList<>(Arrays.asList(split)));
    }

    public static void dfs(TreeNode root, StringBuilder stringBuilder) {
        if (root == null) stringBuilder.append("N,");
        else {
            stringBuilder.append(root.val).append(",");
            dfs(root.left, stringBuilder);
            dfs(root.right, stringBuilder);
        }
    }

    public static TreeNode dfs(List<String> list) {
        final String s = list.get(0);
        if ("N".equals(s)) return null;
        else {
            final TreeNode root = new TreeNode(Integer.parseInt(s));
            list.remove(0);
            root.left = dfs(list);
            list.remove(0);
            root.right = dfs(list);
            return root;
        }
    }
}
