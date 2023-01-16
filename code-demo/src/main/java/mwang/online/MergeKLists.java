package mwang.online;

/**
 * 23. 合并K个升序链表 - hard(分治递归)
 * https://leetcode.cn/problems/merge-k-sorted-lists/description/
 */
public class MergeKLists {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1, new ListNode(3));
        ListNode list2 = new ListNode(2, new ListNode(4));
        ListNode listNode = mergeKLists(new ListNode[]{list1, list2});
        show(listNode);
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

    public static void show(ListNode listNode) {
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}



