package mwang.online.classic150;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/3/15 15:55
 * @description: No134_GasStation
 */
public class No134_GasStation {

    @Test
    public void test() {
        // case1
        int[] gas = new int[]{1, 2, 3, 4, 5};
        int[] cost = new int[]{3, 4, 5, 1, 2};
        int res = canCompleteCircuit(gas, cost);
        Assert.assertEquals(res, 3);
        // case2
        gas = new int[]{2, 3, 4};
        cost = new int[]{3, 4, 3};
        res = canCompleteCircuit(gas, cost);
        Assert.assertEquals(res, -1);
        // case3
        gas = new int[]{5, 1, 2, 3, 4};
        cost = new int[]{4, 4, 1, 5, 1};
        res = canCompleteCircuit(gas, cost);
        Assert.assertEquals(res, 4);
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        final int n = gas.length;
        final int[] remains = new int[n];
        int total = 0;
        // remain gas
        for (int i = 0; i < n; i++) {
            remains[i] = gas[i] - cost[i];
            total += remains[i];

        }
        if (total < 0) {
            return -1;
        }
        System.out.println(Arrays.toString(remains));
        // find start
        int start = 0;
        while (start < n) {
            // if from start
            int remainTotal = 0;
            int i = start;
            for (; i < n; i++) {
                remainTotal += remains[(i + n) % n];
                if (remainTotal < 0) break;
            }
            if (i == n) return start;
            // (i-start) is invalid try length
            start += (i-start) + 1;
        }
        return start;
    }
}
