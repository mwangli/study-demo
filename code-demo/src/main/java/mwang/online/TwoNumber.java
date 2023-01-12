package mwang.online;

/**
 * 2. 两数相加 - middle
 * https://leetcode.cn/problems/add-two-numbers/
 */
public class TwoNumber {

    public static void main(String[] args) {
        new TwoNumber().test();
    }

    public void test() {
        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(0);
        ListNode listNode = new TwoNumber().addTwoNumbers(l1, l2);
        show(listNode);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int pre = 0;
        ListNode listNode = null;
        ListNode lastNode = null;
        // 终止条件，无数字且无进位
        while (l1 != null || l2 != null || pre != 0) {
            // 高位不够补零
            if (l1 == null) l1 = new ListNode(0);
            if (l2 == null) l2 = new ListNode(0);
            int total = l1.val + l2.val + pre;
            // 进位处理
            if (total >= 10) {
                pre = 1;
                total -= 10;
            } else {
                pre = 0;
            }
            // 构建无头单链表
            ListNode newNode = new ListNode(total);
            if (listNode == null) {
                listNode = newNode;
                lastNode = newNode;
            } else {
                lastNode.next = newNode;
                lastNode = newNode;
            }
            // 遍历链表
            l1 = l1.next;
            l2 = l2.next;
        }
        return listNode;
    }


    public class ListNode {
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

    public void show(ListNode listNode) {
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}




