package mwang.online.hot100;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/7 09:14
 * @description: No146_LRUCache
 * 最近最少使用
 */
public class No146_LRUCache2 extends LinkedHashMap<Integer, Integer> {

    private final int capacity;

    public static void main(String[] args) {
        final No146_LRUCache2 cache = new No146_LRUCache2(2);
        cache.put(2, 1);
        cache.put(1, 1);

        cache.put(2, 3);
        cache.put(4, 1);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }

    public No146_LRUCache2(int capacity) {
        // 第三个参数控制以访问顺序还是写入顺序排序
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    public boolean removeEldestEntry(Map.Entry entry) {
        // 选择淘汰策略，默认不淘汰
        return size() > capacity;
    }
}
