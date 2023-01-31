package mwang.online.hot100;

import mwang.online.base.ListNode;

/**
 * 23. 合并K个升序链表 - hard(分治递归)
 * https://leetcode.cn/problems/merge-k-sorted-lists/description/
 * 思路：采用分治思想，采用二分到最小单元，再两两合并
 */
public class No23_MergeKLists {

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1, new ListNode(3));
        ListNode list2 = new ListNode(2, new ListNode(4));
        ListNode listNode = mergeKLists(new ListNode[]{list1, list2});
        ListNode.show(listNode);
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public static ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        if (left > right) return null;
        int mid = (left + right) / 2;
        ListNode ll = merge(lists, left, mid);
        ListNode rr = merge(lists, mid + 1, right);
        return mergeTwoLists(ll, rr);
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}



