package sort;

import com.sun.deploy.uitoolkit.impl.awt.AWTDragHelper;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * @author mwangli
 * @date 2022/2/18 9:15
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] ints = new int[80000];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = new Random().nextInt(80000);
        }
        long start = new Date().getTime();
        int[] sort = sort(ints);
        long end = new Date().getTime();
        System.out.println("耗时：" + (end - start));
//        System.out.println("原始：" + Arrays.toString(ints));
//        System.out.println("结果：" + Arrays.toString(sort));
    }

    private static int[] sort(int[] arrays) {
        int[] ints = new int[arrays.length];
        ints[0] = arrays[0];
        for (int i = 1; i < arrays.length; i++) {
            int insertNum = arrays[i];
            int j = i - 1;
            while (j >= 0 && insertNum <= ints[j]) {
                ints[j + 1] = ints[j];
                j--;
            }
            ints[j + 1] = insertNum;
        }
        return ints;
    }
}
