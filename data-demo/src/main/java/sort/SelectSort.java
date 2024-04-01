package sort;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * @author mwangli
 * @date 2022/2/17 16:54
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] ints = new int[80000];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = new Random().nextInt(8000);
        }
        long start = new Date().getTime();
        int[] sort = sort(ints);
        long end = new Date().getTime();
        System.out.println("耗时：" + (end - start));
//        System.out.println("结果：" + Arrays.toString(sort));
    }

    /**
     * 每一趟排序从未排序的列表中选择出最小的数字，交换到最前面的位置
     * 共执行n-1趟排序，每趟排序执行1-(n-1)次比较，执行一次交换
     * 80000随机数排序耗时：1931ms
     */
    private static int[] sort(int[] arrays) {
        for (int i = 0; i < arrays.length - 1; i++) {
            int minIndex = i;
            boolean f = false;
            for (int j = i + 1; j < arrays.length; j++) {
                if (arrays[j] < arrays[minIndex]) {
                    minIndex = j;
                    f = true;
                }
            }
            if (f) {
                int temp = arrays[i];
                arrays[i] = arrays[minIndex];
                arrays[minIndex] = temp;
            }
        }
        return arrays;
    }
}
