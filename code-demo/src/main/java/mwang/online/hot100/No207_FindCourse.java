package mwang.online.hot100;

import java.util.*;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/5/10 10:13
 * @description: No207_FindfCourse
 */
public class No207_FindCourse {

    public static void main(String[] args) {
        final int[][] ints = {{1, 0}};
        System.out.println(canFinish(2, ints));

    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // 表示前置课程的数量
        final ArrayList<Integer> indeg = new ArrayList<>();
        // 保存课程关系
        final ArrayList<List<Integer>> edge = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            // 初始化数据
            indeg.add(0);
            edge.add(new ArrayList<>());
        }
        for (final int[] prerequisite : prerequisites) {
            //  a -> b
            final int a = prerequisite[0];
            final int b = prerequisite[1];
            // 表示课程b的前置课程增加1
            indeg.set(b, indeg.get(b) + 1);
            // 表示通过课程a可以学习课程b
            edge.get(a).add(b);
        }
        // 前置课程数量为0，则代表可学习
        final Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indeg.size(); i++) {
            if (indeg.get(i) == 0) queue.offer(i);
        }
        // 学习一个课程，则更新后续课程的前置课程数量
        // 因为数据不会重复，所以可以减一而不用判断
        // 例如 1->2,1->3 不会重复出现 1->2
        while (!queue.isEmpty()) {
            final List<Integer> courseList = edge.get(queue.poll());
            courseList.forEach(c -> {
                indeg.set(c, indeg.get(c) - 1);
                // 满足前置课程，则加入可学习队列
                if (indeg.get(c) == 0) queue.offer(c);
            });
        }
        return indeg.stream().allMatch(c -> c == 0);
    }
}
