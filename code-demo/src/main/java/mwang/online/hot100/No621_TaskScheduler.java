package mwang.online.hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/5/8 10:42
 * @description: No621_TaskScheduler
 * 思路：填表法；
 * res =  (max-1)*(n+1)+maxCount or task.size
 */
public class No621_TaskScheduler {

    public static void main(String[] args) {
        final char[] chars = {'A', 'A', 'A', 'B', 'B', 'B'};
        System.out.println(leastInterval(chars, 2));
    }

    public static int leastInterval(char[] tasks, int n) {
        // 统计每个任务出现的次数
        final HashMap<Character, Integer> map = new HashMap<>();
        for (char task : tasks)
            map.put(task, map.getOrDefault(task, 0) + 1);
        // 找到出现任务出现最多的次数和任务个数
        AtomicInteger max = new AtomicInteger();
        AtomicInteger maxCount = new AtomicInteger();
        map.forEach((k, v) -> {
            if (v > max.get()) {
                maxCount.set(1);
                max.set(v);
            }
            if (v == max.get()) {
                maxCount.getAndIncrement();
            }
        });
        return Math.max(tasks.length, (max.get() - 1) * (n + 1) + maxCount.get() - 1);
    }

    public static int leastInterval2(char[] tasks, int n) {
        // 统计每个任务出现的次数
        final HashMap<Character, Integer> map = new HashMap<>();
        for (char task : tasks)
            map.put(task, map.getOrDefault(task, 0) + 1);
        // 每个任务的下次可执行时间
        final ArrayList<Integer> nextTime = new ArrayList<>();
        // 每个任务的剩余执行次数
        final ArrayList<Integer> restTime = new ArrayList<>();
        map.forEach((k, v) -> {
            nextTime.add(1);
            restTime.add(v);
        });
        int s = nextTime.size();
        int l = tasks.length;
        int time = 0;
        while (l-- > 0) {
            // 获取待执行任务中最早的下次可执行时间
            int minTime = Integer.MAX_VALUE;
            for (int i = 0; i < s; i++) {
                if (restTime.get(i) > 0)
                    minTime = Math.min(nextTime.get(i), minTime);
            }
            // 如果存在空闲任务时间
            if (minTime > time)
                time = minTime;
            // 获取最早可执行时间中剩余次数最多的任务的下标
            int maxIndex = -1;
            for (int i = 0; i < s; i++) {
                if (restTime.get(i) > 0 && nextTime.get(i) <= time)
                    if (maxIndex == -1 || restTime.get(i) > restTime.get(maxIndex))
                        maxIndex = i;
            }
            // 更新任务剩余状态
            restTime.set(maxIndex, restTime.get(maxIndex) - 1);
            nextTime.set(maxIndex, time + n + 1);
            time++;
        }
        return time - 1;
    }
}
