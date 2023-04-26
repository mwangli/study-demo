package mwang.online.hot100;

import java.util.Arrays;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/4/25 17:00
 * @description: No406_ReconstructQueue
 */
public class No406_ReconstructQueue {

    public static void main(String[] args) {
//        final int[][] ints = new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        final int[][] ints = new int[][]{{2, 4}, {3, 4}, {9, 0}, {0, 6}, {7, 1}, {6, 0}, {7, 3}, {2, 5}, {1, 1}, {8, 0}};
        System.out.println(Arrays.deepToString(reconstructQueue(ints)));
    }


    public static int[][] reconstructQueue(int[][] people) {
        // 按第一位升序，第二位降序排列
        Arrays.sort(people, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        final int[][] res = new int[people.length][];
        for (final int[] person : people) {
            int k = person[1];
            for (int j = 0; j < res.length; j++) {
                // 跳过前面k个空位
                if (res[j] == null) {
                    if (k > 0) {
                        k--;
                        continue;
                    }
                    res[j] = person;
                    break;
                }
            }
        }
        return res;
    }

}
