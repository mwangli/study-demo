package sort;

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
     * 80000随机数排序耗时：8ms
     */
    private static int[] sort(int[] arrays) {
        // 构建大顶堆
        for (int i = arrays.length / 2 - 1; i >= 0; i--) {
            adjust(arrays, i, arrays.length);
        }
        for (int l = arrays.length - 1; l > 0; l--) {
            // 交换末尾元素
            int t = arrays[0];
            arrays[0] = arrays[l];
            arrays[l] = t;
            // 重新调整大顶堆
            adjust(arrays, 0, l);
        }
        return arrays;
    }

    private static void adjust(int[] arrays, int i, int length) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int maxIndex = i;
        if (left < length && arrays[left] > arrays[maxIndex]) {
            maxIndex = left;
        }
        if (right < length && arrays[right] > arrays[maxIndex]) {
            maxIndex = right;
        }
        if (maxIndex != i) {
            int t = arrays[maxIndex];
            arrays[maxIndex] = arrays[i];
            arrays[i] = t;
            adjust(arrays, maxIndex, length);
        }
    }

    private static void adjust2(int[] arrays, int i, int length) {
        for (int k = 2 * i + 1; k < length; i = k, k = k * 2 + 1) {
            if (k + 1 < length && arrays[k + 1] > arrays[k]) {
                k++;
            }
            if (arrays[k] > arrays[i]) {
                int t = arrays[k];
                arrays[k] = arrays[i];
                arrays[i] = t;
            } else {
                break;
            }
        }
    }
}
