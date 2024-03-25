package mwang.online.hot100;

import java.util.*;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/4/24 10:24
 * @description: No347_TopKFrequent
 * <p>
 * 出现频率最高的K个数
 * 1. 优先队列 N*LogK 线性代数阶复杂度
 */
public class No347_TopKFrequent {

    public static void main(String[] args) {
//        final int[] ints = new int[]{1, 1, 1, 2, 2, 3};
        final int[] ints = new int[]{1, 1, 1, 2, 2, 3};
        System.out.println(Arrays.toString(topKFrequent(ints, 2)));
    }


    public static int[] topKFrequent(int[] nums, int k) {
        final int[] res = new int[k];
        int index = 0;
        final HashMap<Integer, Integer> map = new HashMap<>();
        for (final int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        final PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((e1, e2) -> e2.getValue() - e1.getValue());
        map.entrySet().forEach(e -> {
            if (queue.size() == k) {
                assert queue.peek() != null;
                if (e.getValue() > queue.peek().getValue()) {
                    queue.poll();
                    queue.offer(e);
                }
            } else {
                queue.offer(e);
            }
        });
        while (!queue.isEmpty())
            res[index++] = Objects.requireNonNull(queue.poll()).getKey();
        return res;
    }

    public static int[] topKFrequent2(int[] nums, int k) {
        final int[] res = new int[k];

        return res;
    }
}
