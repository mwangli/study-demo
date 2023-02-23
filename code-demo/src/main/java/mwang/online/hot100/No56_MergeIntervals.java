package mwang.online.hot100;

import java.util.*;

/**
 * 56. 合并区间
 * 思路：1。按区间左端点进行排序
 * 2。遍历区间列表，判断当前区间能否于结果集最后一个区间合并
 * 3。能合并则合并，不能则将当前区间将加入结果集
 */
public class No56_MergeIntervals {

    public static void main(String[] args) {
        No56_MergeIntervals o = new No56_MergeIntervals();
        int[][] ints = {{1, 4}, {4, 5}};
        int[][] ans = o.merge(ints);
        System.out.println(Arrays.deepToString(ans));
    }

    public int[][] merge(int[][] intervals) {
        // 按左区间排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> res = new ArrayList<>();
        for (var interval : intervals) {
            // 初始状态和没有交集 newLeft > oldRight
            // int[] lastInterval = res.get(res.size() - 1);
            // 短路运算避免空集合判断
            if (res.size() == 0 || interval[0] > res.get(res.size() - 1)[1]) {
                res.add(interval);
            } else {
                // 有交集，则可以合并，左区间不变，右区间取最大值
                int[] lastInterval = res.get(res.size() - 1);
                lastInterval[1] = Math.max(lastInterval[1], interval[1]);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
