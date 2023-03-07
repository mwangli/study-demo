package mwang.online.hot100;

import mwang.online.base.ListNode;

import java.util.HashSet;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/6 10:39
 * @description: No141_CycleLinkedList
 * 141. 环形链表 验证是否被2次访问
 */
public class No141_CycleLinkedList {
    public static void main(String[] args) {
        final ListNode listNode1 = new ListNode(1);
        final ListNode listNode2 = new ListNode(2);
        listNode1.next = listNode2;
        listNode2.next = listNode1;
        System.out.println(hasCycle2(listNode1));
    }

    public static boolean hasCycle(ListNode head) {
        int pos = 0;
        while (head.next != null && pos <= 10000) {
            head = head.next;
            pos++;
        }
        return pos > 10000;
    }

    public static boolean hasCycle2(ListNode head) {
        final HashSet<ListNode> set = new HashSet<>();
        while (head.next != null) {
            if (!set.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }


}
