package mwang.online.classic150;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/3/15 09:32
 * @description: No274_HIndex
 */
public class No274_HIndex {

    @Test
    public void test() {
        int[] nums1 = {3, 0, 6, 1, 5};
        int[] nums2 = {1, 3, 1};
        int res1 = 3;
        int res2 = 1;
        Assert.assertEquals(hIndex(nums1), res1);
        Assert.assertEquals(hIndex(nums2), res2);
    }

    // O(nlogn) O(nlogn)
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0, i = citations.length - 1;
        while (i >= 0 && citations[i] > h) {
            // 找到一篇至少被引用h+1次的论文，则将h+1
            h++;
            i--;
        }
        return h;
    }

    // O(n) O(n)
    public int hIndex3(int[] citations) {
        int n = citations.length;
        // count[i]表示引用数量等于i的论文有几篇;
        int[] count = new int[n + 1];
        for (int citation : citations) {
            // h值必然小于n,引用数量大于n也做n计算
            final int value = Math.min(citation, n);
            count[value]++;
        }
        // 统计论文的数量
        int total = 0;
        for (int i = n; i > 0; i--) {
            // 此处total不用归零
            // 因为引用数量大于等于i的论文数量，势必也可以计算到引用数量大于等于i-1的论文数量中
            // 例如，count[7] = 2, count[6] = 3, 计算引用数量大于等于6的论文数量时 = 2+3
            // 再往前推同理
            total += count[i];
            if (total >= i) {
                return i;
            }
        }
        return 0;
    }

    // O(n2) O(1)
    public int hIndex2(int[] citations) {
        int maxValue = citations.length;
        // 从大到小，依次穷举
        while (maxValue > 0) {
            int count = 0;
            for (int citation : citations) {
                if (citation >= maxValue) {
                    count++;
                }
                if (count >= maxValue)
                    return maxValue;
            }
            maxValue--;
        }
        return 0;
    }
}

