package sort;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * @author mwangli
 * @date 2022/2/23 17:13
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(80000);
        }
        long start = new Date().getTime();
        sort(arr);
        long end = new Date().getTime();
        System.out.println("耗时：" + (end - start));
//        System.out.println("结果：" + Arrays.toString(arr));

    }

    /**
     * 依次按照从低位到高位将数据放到顺序桶中，再从桶中以此取出，先保证低位有序，再保证高位有序
     * 最高位处理完毕，数据有序，需要额外空间开销int[11][N]
     * 80000随机数排序耗时：18ms
     */
    private static void sort(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int length = (max + "").length();
        int[][] bucket = new int[10][arr.length];
        int[] bucketCount = new int[10];
        for (int i = 0, j = 1; i < length; i++, j *= 10) {
            for (int a : arr) {
                int index = a / j % 10;
                bucket[index][bucketCount[index]++] = a;
            }
            int t = 0;
            for (int k = 0; k < 10; k++) {
                if (bucketCount[k] > 0) {
                    for (int m = 0; m < bucketCount[k]; m++) {
                        arr[t++] = bucket[k][m];
                    }
                    bucketCount[k] = 0;
                }
            }
        }
    }
}
