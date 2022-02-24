package sort;

import com.sun.deploy.uitoolkit.impl.awt.AWTDragHelper;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * @author mwangli
 * @date 2022/2/22 14:49
 */
public class QuickSort {


    public static void main(String[] args) {
        int[] ints = new int[80000];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = new Random().nextInt(80000);
        }
        long start = new Date().getTime();
        sort(ints, 0, ints.length - 1);
        long end = new Date().getTime();
        System.out.println("耗时：" + (end - start));
//        System.out.println("结果：" + Arrays.toString(ints));
    }

    /**
     * 先找到基准值，再按大小分成左右两边
     * 递归处理，直至全列表有序
     * 80000随机数排序耗时：16ms
     */
    private static void sort(int[] arr, int left, int right) {
        if (left >= right) return;
        int key = arr[left];
        int l = left;
        int r = right;
        while (left < right) {
            while (left < right && arr[right] >= key) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= key) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[right] = key;
        sort(arr, l, left - 1);
        sort(arr, right + 1, r);
    }
}
