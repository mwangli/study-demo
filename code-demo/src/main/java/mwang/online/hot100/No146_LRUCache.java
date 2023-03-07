package mwang.online.hot100;

import java.util.HashMap;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/7 09:14
 * @description: No146_LRUCache
 * 最近最少使用
 */
public class No146_LRUCache {

    static class LinkedNode {
        int key;
        int value;
        LinkedNode pre;
        LinkedNode next;
    }

    LinkedNode head;
    LinkedNode tail;
    HashMap<Integer, LinkedNode> cache;
    Integer capacity;
    Integer size;


    public static void main(String[] args) {
        final No146_LRUCache cache = new No146_LRUCache(2);
        cache.put(2, 1);
        cache.put(1, 1);

        cache.put(2, 3);
        cache.put(4, 1);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }

    public No146_LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.head = new LinkedNode();
        this.tail = new LinkedNode();
        head.next = tail;
        tail.pre = head;
        this.cache = new HashMap<>();
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            final LinkedNode node = cache.get(key);
            // 删除node
            node.pre.next = node.next;
            node.next.pre = node.pre;
            // 将node添加到头部
            head.next.pre = node;
            node.next = head.next;
            head.next = node;
            node.pre = head;

            return node.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            LinkedNode node = cache.get(key);
            node.value= value;
            // 删除node
            node.pre.next = node.next;
            node.next.pre = node.pre;
            // 将node添加到头部
            head.next.pre = node;
            node.next = head.next;
            head.next = node;
            node.pre = head;
        } else {
            LinkedNode node = new LinkedNode();
            node.key = key;
            node.value = value;
            // 将node添加到头部
            head.next.pre = node;
            node.next = head.next;
            head.next = node;
            node.pre = head;

            cache.put(key, node);
            size++;
            if (size > capacity) {
                // 找到最后一个节点
                LinkedNode deletedNode = tail.pre;
                // 删除node
                deletedNode.pre.next = deletedNode.next;
                deletedNode.next.pre = deletedNode.pre;
                size--;
                // 删除key
                cache.remove(deletedNode.key);
            }
        }
    }
}
