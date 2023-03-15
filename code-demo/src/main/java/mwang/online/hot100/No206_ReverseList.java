package mwang.online.hot100;

import mwang.online.base.ListNode;

import java.util.Stack;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/9 13:11
 * @description: No206_ReverseList
 * 206. 反转链表
 */
public class No206_ReverseList {

    public static void main(String[] args) {
        final ListNode head = new ListNode(1, new ListNode(2, new ListNode(3)));
        final ListNode head2 = new ListNode(1, new ListNode(2, new ListNode(3)));
        final ListNode head3 = new ListNode(1, new ListNode(2, new ListNode(3)));
        final ListNode head4 = new ListNode(1, new ListNode(2, new ListNode(3)));
        System.out.println(reverseList(head));
        System.out.println(reverseList2(head2));
        System.out.println(reverseList3(head3));
        System.out.println(reverseList4(head4));

    }

    public static ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode cur = head;
        while (cur != null) {
            // 头插法
            ListNode next = cur.next;
            cur.next = dummy.next;
            dummy.next = cur;
            cur = next;
        }
        return dummy.next;
    }

    public static ListNode reverseList3(ListNode head) {
        // 将next指针修改为指向前一节点，返回尾结点即可
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }


    public static ListNode reverseList2(ListNode head) {
        // 利用栈后进先出的性质
        final Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        ListNode pre = new ListNode();
        ListNode cur = pre;
        while (!stack.isEmpty()) {
            cur.next = stack.pop();
            cur = cur.next;
        }
        cur.next = null;
        return pre.next;
    }

    public static ListNode reverseList4(ListNode head) {
        if (head == null || head.next == null) return head;
        // 分为头部(head)和尾部(next)两部分
        ListNode next = head.next;
        // 切断指向尾部指针 head -> next
        head.next = null;
        ListNode reversedList = reverseList4(next);
        // 将尾部指向头部 next->head->null
        next.next = head;
        // 返回尾部节点
        return reversedList;
    }
}
