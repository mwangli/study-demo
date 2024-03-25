package mwang.online.hot100;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/30 09:24
 * @description:
 * TODO 优先队列原理
 */
public class No239_MaxSlidingWindow {

    public static void main(String[] args) {
        final int[] ints = {5, 4, 3, 2, 1};
        final int[] res = maxSlidingWindow2(ints, 2);
        System.out.println(Arrays.toString(res));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        // 使用最大值优先队列，存储数值和对应下标
        // 将元素放入优先队列的时间复杂度为 logN,总复杂度为 NlogN
        final PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        // 初始化第一个窗口
        for (int i = 0; i < k; i++) {
            queue.offer(new int[]{nums[i], i});
        }
        final int[] res = new int[nums.length - k + 1];
        res[0] = queue.peek()[0];
        // 计算后续窗口
        for (int i = 1; i < res.length; i++) {
            queue.offer(new int[]{nums[i - 1 + k], i - 1 + k});
            // 只判断当前最大元素是否超出窗口范围，允许队列中存在超出范围的非最大值
            while (queue.peek()[1] <= i - 1) {
                queue.poll();
            }
            res[i] = queue.peek()[0];
        }
        return res;
    }

    public static int[] maxSlidingWindow2(int[] nums, int k) {
        // 使用双端队列，存储窗口范围内的下标值，并保证队列中下标对应的值保持单调递减(单调队列)
        final LinkedList<Integer> queue = new LinkedList<>();
        // 初始化第一个窗口
        for (int i = 0; i < k; i++) {
            while (!queue.isEmpty() && nums[i] > nums[queue.peekLast()]){
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        final int[] res = new int[nums.length - k + 1];
        res[0] = nums[queue.peek()];
        // 计算后续窗口 num[i-1+k] 为窗口最后一个元素
        for (int i = 1; i < res.length; i++) {
            while (!queue.isEmpty() && nums[i - 1 + k] > nums[queue.peekLast()]){
                queue.pollLast();
            }
            queue.offerLast(i - 1 + k);
            // 只判断当前最大元素是否超出窗口范围，允许队列中存在超出范围的非最大值
            while (queue.peekFirst() <= i-1){
                queue.poll();
            }
            res[i] = nums[queue.peekFirst()];
        }
        return res;
    }
}
