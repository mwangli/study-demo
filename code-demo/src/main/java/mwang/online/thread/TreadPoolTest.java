package mwang.online.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/29 16:34
 * @description: TreadPoolTest
 */
public class TreadPoolTest {

    public static void main(String[] args) {

        final ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 3,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));
        for (int i = 1; i <= 15; i++) {
            int finalI = i;
            executor.execute(() -> {
                SleepUtils.second(3);
                System.out.println(Thread.currentThread().getName() +"_task_" + finalI + " finished");
            });
            printSize(executor);
        }

        SleepUtils.second(3);
        System.out.println("after 3 s:");
        for (int i = 16; i <= 25; i++) {
            int finalI = i;
            executor.execute(() -> {
                SleepUtils.second(3);
                System.out.println(Thread.currentThread().getName() +"_task_" + finalI + " finished");
            });
            printSize(executor);
        }
        printSize(executor);

        SleepUtils.second(3);
        System.out.println("after 6 s:");
        printSize(executor);
        SleepUtils.second(3);
        System.out.println("after 9 s:");
        printSize(executor);

        SleepUtils.second(3);
        executor.shutdown();
    }

    public static void printSize(ThreadPoolExecutor executor) {
        System.out.println("getLargestPoolSize():" + executor.getLargestPoolSize());
        System.out.println("getPoolSize():" + executor.getPoolSize());
//        System.out.println("getCorePoolSize():" + executor.getCorePoolSize());
//        System.out.println("getMaximumPoolSize():" + executor.getMaximumPoolSize());
        System.out.println("getTaskCount():" + executor.getTaskCount());
        System.out.println("getQueue().size():" + executor.getQueue().size());
        System.out.println("getActiveCount():" + executor.getActiveCount());
        System.out.println("--------------------------------");
    }
}
