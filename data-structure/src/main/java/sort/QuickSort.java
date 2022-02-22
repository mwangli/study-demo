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
        int[] ints = {5,3,6,2,4,6,8,1,5};
        for (int i = 0; i < ints.length; i++) {
//            ints[i] = new Random().nextInt(80000);
        }
        long start = new Date().getTime();
        sort(ints, 0, ints.length - 1);
        long end = new Date().getTime();
        System.out.println("耗时：" + (end - start));
        System.out.println("结果：" + Arrays.toString(ints));
    }

    private static void sort(int[] arrays, int left, int right) {
        if (left >= right) return;
        int key = arrays[left];
        int l = left;
        int r = right;
        while (left < right) {
            while (left < right && arrays[right] >= key) {
                right--;
            }
            arrays[left] = arrays[right];
            while (left < right && arrays[left] <= key) {
                left++;
            }
            arrays[right] = arrays[left];
        }
        arrays[left] = key;
        sort(arrays, l, left - 1);
        sort(arrays, right + 1, r);
    }

}
