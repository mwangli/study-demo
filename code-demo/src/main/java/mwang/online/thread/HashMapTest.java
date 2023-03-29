package mwang.online.thread;

import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/24 15:40
 * @description: HashMapTest
 */
public class HashMapTest {

    @SneakyThrows
    public static void main(String[] args) {
        final HashMap<String, String> map = new HashMap<String, String>(2);
        final CountDownLatch start = new CountDownLatch(1);
        Thread t = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                new Thread(() -> {
                    try {
                        start.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    map.put(UUID.randomUUID().toString(), "");
                }, "ftf" + i).start();
            }
        }, "ftf");
        t.start();
        start.countDown();
        t.join();
    }
}
