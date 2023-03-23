package mwang.online.hot100;

import mwang.online.base.ListNode;

import java.util.Stack;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/23 10:14
 * @description: No234_PalindromeList
 */
public class No234_PalindromeList {

    public static void main(String[] args) {
        final ListNode root = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(2, new ListNode(1)))));
        System.out.println(isPalindrome(root));
    }

    public static boolean isPalindrome(ListNode head) {
        ListNode p1 = head, p2 = head;
        Stack<ListNode> stack = new Stack<>();
        boolean flag = false;
        while (p2 != null) {
            stack.push(p1);
            p1 = p1.next;
            p2 = p2.next;
            if (p2 == null) {
                flag = true;
            } else {
                p2 = p2.next;
            }
        }
        // 处理长度为奇数和偶数
        if (flag) stack.pop();
        while (p1 != null) {
            final ListNode pop = stack.pop();
            if (pop.val != p1.val) return false;
            p1 = p1.next;
        }
        return true;
    }
}
