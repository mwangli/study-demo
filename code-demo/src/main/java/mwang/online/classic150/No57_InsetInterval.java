package mwang.online.classic150;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/4/19 09:56
 * @description: No57_InsetInterval
 */
public class No57_InsetInterval {

    @Test
    public void test() {
        // [1,2],[3,5],[6,7],[8,10],[12,16]
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = {4, 8};
        System.out.println(Arrays.deepToString(insert(intervals, newInterval)));
    }


    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        ArrayList<int[]> res = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    res.add(new int[]{left, right});
                    placed = true;
                }
                res.add(interval);
            } else if (interval[1] < left) {
                // 在插入区间的左侧且无交集
                res.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            res.add(new int[]{left, right});
        }
        int[][] ints = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            ints[i] = res.get(i);
        }
        return ints;
    }
}
