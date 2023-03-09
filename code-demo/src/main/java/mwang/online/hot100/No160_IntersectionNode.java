package mwang.online.hot100;

import mwang.online.base.ListNode;

import java.util.HashSet;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/8 16:54
 * @description: No160_IntersectionNode
 * 160. 相交链表
 * 思路
 */
public class No160_IntersectionNode {

    public static void main(String[] args) {
        final ListNode node1 = new ListNode(1);
        final ListNode node2 = new ListNode(2);
        final ListNode node3 = new ListNode(3);
        node1.next = node3;
//        node2.next = node3;
        System.out.println(getIntersectionNode2(node1, node1));
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        final HashSet<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB)) break;
            headB = headB.next;
        }
        return headB;
    }

    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}
