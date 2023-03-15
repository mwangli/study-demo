package mwang.online.hot100;

import mwang.online.base.ListNode;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/7 19:31
 * @description: No148_SortLinkedList
 * 148. 排序链表，时间复杂度要求线性对数阶，空间复杂度要求常数阶
 * 使用归并排序
 */
public class No148_SortLinkedList {

    public static void main(String[] args) {
        final ListNode head = new ListNode(2, new ListNode(1, new ListNode(3)));
        ListNode pre = new ListNode(4,head);
        show(pre);
        System.out.println();
        show(sortList(pre));
    }

    public static ListNode sortList(ListNode head) {
        return sort(head, null);
    }


    public static ListNode sort2(ListNode head, ListNode tail) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        // 每次合并
        for (int i = 1; i <= length; i <<= 1) {
            for (int j = 0; j < length; j += i) {
                final ListNode list1 = subList(head, j, i);
                final ListNode list2 = subList(head, i, i);
            }
        }
        System.out.println(length);
        return null;
    }

    public static ListNode subList(ListNode head, int start, int length) {
        while (start-- > 0) head = head.next;
        final ListNode pre = new ListNode();
        ListNode cur = pre;
        while (length-- > 0) {
            cur.next = new ListNode(cur.val);
            cur = cur.next;
            head = head.next;
        }
        return pre.next;
    }

    public static ListNode sort(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        // tail节点不参与排序，说明已经完成了最小切分
        // 排序区间为 [head,mid) + [mid,head)
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        final ListNode node1 = sort(head, mid);
        final ListNode node2 = sort(mid, tail);
        return merge(node1, node2);
    }


    public static ListNode merge(ListNode node1, ListNode node2) {
        final ListNode head = new ListNode();
        ListNode cur = head;
        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                cur.next = new ListNode(node1.val);
                node1 = node1.next;
            } else {
                cur.next = new ListNode(node2.val);
                node2 = node2.next;
            }
            cur = cur.next;
        }
        while (node1 != null) {
            cur.next = node1;
            node1 = node1.next;
            cur = cur.next;
        }
        while (node2 != null) {
            cur.next = node2;
            node2 = node2.next;
            cur = cur.next;
        }
        return head.next;
    }

    public static void swapValue(ListNode node1, ListNode node2) {
        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }

    public static void show(ListNode head) {
        while (head != null) {
            System.out.printf("%4d", head.val);
            head = head.next;
        }
    }
}
