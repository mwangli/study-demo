package sort;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * @author mwangli
 * @date 2022/2/17 16:46
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] ints = new int[80000];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = new Random().nextInt(80000);
        }
        long start = new Date().getTime();
        int[] sort = sort(ints);
        long end = new Date().getTime();
        System.out.println("耗时：" + (end - start));
//        System.out.println("结果：" + Arrays.toString(sort));
    }

    /**
     * 通过构建大/小顶堆将最大/小元素放在堆顶
     * 再将堆顶元素和末位元素交换
     * 将剩下元素以上步骤直至完全有序
     * 80000随机数排序耗时：15891ms
     */
    private static int[] sort(int[] arrays) {
        for (int l = arrays.length; l > 0; l--) {
            // 构建大顶堆
            for (int i = arrays.length / 2 - 1; i >= 0; i--) {
                adjust(arrays, i, l);
            }
            // 交换末尾元素
            int t = arrays[0];
            arrays[0] = arrays[l - 1];
            arrays[l - 1] = t;
        }
        return arrays;
    }

    private static void adjust(int[] arrays, int i, int length) {
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arrays[k + 1] > arrays[k]) {
                k = k + 1;
            }
            if (arrays[k] > arrays[i]) {
                int t = arrays[k];
                arrays[k] = arrays[i];
                arrays[i] = t;
            }
        }

    }
}
