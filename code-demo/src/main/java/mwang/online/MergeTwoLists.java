package mwang.online;

import mwang.online.base.ListNode;

/**
 * 21. 合并两个有序链表 - easy
 * https://leetcode.cn/problems/merge-two-sorted-lists/description/
 */
public class MergeTwoLists {

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1, new ListNode(3));
        ListNode list2 = new ListNode(2, new ListNode(4));
        ListNode listNode = mergeTwoLists2(list1, list2);
        ListNode.show(listNode);
    }

    public static ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        if (list1.val < list2.val) {
            list1.next = mergeTwoLists2(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists2(list1, list2.next);
            return list2;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode pre = new ListNode();
        ListNode head = pre;
        ListNode cru1 = list1;
        ListNode cru2 = list2;
        while (cru1 != null && cru2 != null) {
            if (cru1.val < cru2.val) {
                pre.next = new ListNode(cru1.val);
                cru1 = cru1.next;
            } else {
                pre.next = new ListNode(cru2.val);
                cru2 = cru2.next;
            }
            pre = pre.next;
        }
        while (cru1 != null) {
            pre.next = new ListNode(cru1.val);
            cru1 = cru1.next;
            pre = pre.next;
        }
        while (cru2 != null) {
            pre.next = new ListNode(cru2.val);
            cru2 = cru2.next;
            pre = pre.next;
        }
        return head.next;
    }
}



