package mwang.online.hot100;

import mwang.online.base.ListNode;

/**
 * 24. 两两交换链表中的节点 - middle
 * https://leetcode.cn/problems/swap-nodes-in-pairs/description/
 *
 * 思路：可使用递归交换当前节点和剩余节点，直到最后节点
 */
public class No24_SwapListNode {

    public static void main(String[] args) {
        ListNode list = new ListNode(1, new ListNode(2, new ListNode(3)));
        ListNode.show(swapPairs2(list));
    }

    public static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode temp = head.next;
        head.next = swapPairs2(temp.next);
        temp.next = head;
        return temp;
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode cur = pre;
        while (cur.next != null && cur.next.next != null) {
            // 交换后面两个相邻节点
            ListNode temp = cur;
            ListNode node1 = cur.next;
            ListNode node2 = cur.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            cur = cur.next.next;
        }
        return pre.next;
    }
}
