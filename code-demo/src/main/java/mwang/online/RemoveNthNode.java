package mwang.online;

import mwang.online.base.ListNode;

/**
 * 19. 删除链表的倒数第 N 个结点 - middle
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/
 */
public class RemoveNthNode {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode node = removeNthFromEnd2(listNode, 1);
        ListNode.show(node);
    }

    /**
     * 双指针法，first,second 两个指针间隔为n，当 first 指向末尾时，second 指向的即为倒数第n个元素
     */
    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        // 构建前驱头节点
        ListNode pre = new ListNode();
        pre.next = head;
        // first指针先遍历N个节点
        ListNode firstNode = head;
        while (n-- > 0) {
            firstNode = firstNode.next;
        }
        // second指向待删除节点，last辅助储存上一节点
        ListNode secondNode = head;
        ListNode lastNode = pre;
        while (firstNode != null) {
            firstNode = firstNode.next;
            secondNode = secondNode.next;
            lastNode = lastNode.next;
        }
        // 删除节点
        lastNode.next = secondNode.next;
        return pre.next;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int length = getLength(head);
        int k = length - n;
        // 构建前驱头节点
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode last = pre;
        ListNode current = head;
        // 找到待删除节点和上一节点
        while (k-- > 0) {
            last = last.next;
            current = current.next;
        }
        // 删除节点
        last.next = current.next;
        return pre.next;
    }

    private static int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }
}
