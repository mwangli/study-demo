package mwang.online.hot100;

import mwang.online.base.ListNode;

import java.util.HashSet;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/6 17:08
 * @description: No142_DetectedCycleList
 * 142. 环形链表 II
 * 快慢指针第二次相遇
 */
public class No142_DetectedCycleList {

    public static void main(String[] args) {
        final ListNode listNode1 = new ListNode(1);
        final ListNode listNode2 = new ListNode(2);
        listNode1.next = listNode2;
        listNode2.next = listNode1;
        System.out.println(detectedCycle2(listNode1));
    }

    public static ListNode detectedCycle(ListNode head) {
        final HashSet<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {
                return head;
            }
            head = head.next;
        }
        return null;
    }

    public static ListNode detectedCycle2(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;
        while (true) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            if (fast == slow) {
                ListNode pre = head;
                while (pre != fast) {
                    pre = pre.next;
                    fast = fast.next;
                }
                return pre;
            }
        }
    }
}
