package mwang.online.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/29 13:57
 * @description: CountTask
 */
public class CountTask extends RecursiveTask<Long> {

    private static final int THRESHOLD = 200000; // 阈值
    private long start;
    private long end;

    public CountTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        // 如果任务足够小就计算任务
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            long middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            // 执行子任务
            leftTask.fork();
            rightTask.fork();
            // 等待子任务执行完，并得到其结果
            long leftResult = leftTask.join();
            long rightResult = rightTask.join();
            // 合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();
        long start = 1;
        long end = 1000000000L;
        long sum = 0;
        final AtomicLong taskNums = new AtomicLong(1);
        for (long i = start; i <= end; i++) {
            sum += i;
        }
        System.out.println(sum);
        final long endTime = System.currentTimeMillis();
        System.out.println("单任务耗时:" + (endTime - startTime));

        final long startTime1 = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 生成一个计算任务，负责计算1+2+3+4
        CountTask task = new CountTask(start, end   );
        // 执行一个任务
        Future<Long> result = forkJoinPool.submit(task);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        }
        final long endTime1 = System.currentTimeMillis();
        System.out.println("多任务耗时:" + (endTime1 - startTime1));


    }

}
