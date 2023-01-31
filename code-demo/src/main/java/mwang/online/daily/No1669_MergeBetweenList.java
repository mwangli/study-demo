package mwang.online.daily;

import mwang.online.base.ListNode;

/**
 * 1669. 合并两个链表
 * https://leetcode.cn/problems/merge-in-between-linked-lists/description/
 */
public class No1669_MergeBetweenList {

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode list2 = new ListNode(88, new ListNode(99));
        ListNode.show(mergeInBetween(list1, 3, 4, list2));
    }

    public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        // 构建头节点
        ListNode head = new ListNode();
        head.next = list1;
        // 遍历list1,找到a-1节点
        ListNode cur = head;
        while (a > 0) {
            cur = cur.next;
            a--;
            b--;
        }
        ListNode tempA = cur;
        // 接着遍历list1,找到b+1节点
        while (b >= -1) {
            cur = cur.next;
            b--;
        }
        ListNode tempB = cur;
        // 遍历list2,找到尾节点
        cur = list2;
        while (cur.next != null) {
            cur = cur.next;
        }
        ListNode lastNode = cur;
        // 将a-1指向list2头节点
        tempA.next = list2;
        // 将list2尾节点指向b+1
        lastNode.next = tempB;
        return head.next;
    }
}
