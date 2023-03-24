package mwang.online.thread;

import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/24 10:30
 * @description: Cache
 */
public class Cache {
    static Map<String, Object> map = new HashMap<String, Object>();
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static Lock r = rwl.readLock();
    static Lock w = rwl.writeLock();

    // 获取一个key对应的value
    @SneakyThrows
    public static final Object get(String key) {
        r.lock();
        r.lock();
        try {
            return map.get(key);
        } finally {
            r.unlock();
        }
    }

    // 设置key对应的value，并返回旧的value
    public static final Object put(String key, Object value) {
        w.lock();
        try {
            return map.put(key, value);
        } finally {
            w.unlock();
        }
    }

    // 清空所有的内容
    public static final void clear() {
        w.lock();
        try {
            map.clear();
        } finally {
            w.unlock();
        }
    }

    public static void main(String[] args) {
        r.lock();
        w.lock();
        final Cache cache = new Cache();
        System.out.println(cache.put("test","test"));
//        cache.clear();

        final CountDownLatch start = new CountDownLatch(1);

        int threadCount = 2;
        for (int i = 0; i < threadCount; i++) {
            new Thread(()->{
                try {
                    start.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cache.get("test");
            },"read-thread-"+ i).start();
        }

        for (int i = 0; i < threadCount; i++) {
            new Thread(()->{
                try {
                    start.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cache.put("test","test2");
            }, "write-thread-"+ i).start();
        }
        start.countDown();
    }
}
