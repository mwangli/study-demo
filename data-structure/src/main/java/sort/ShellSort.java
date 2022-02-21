package sort;

import com.sun.deploy.uitoolkit.impl.awt.AWTDragHelper;

import javax.management.StandardEmitterMBean;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * @author mwangli
 * @date 2022/2/18 14:32
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] ints = new int[80000];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = new Random().nextInt(80000);
        }
        long start = new Date().getTime();
        int[] sort = sort2(ints);
        long end = new Date().getTime();
        System.out.println("耗时：" + (end - start));
//        System.out.println("结果：" + Arrays.toString(sort));
    }

    /**
     * 对数据进行增量分组，每组内进行插入排序或者冒泡排序
     * 当增量递减为1时，排序完成
     * 通过逐步增加数据的有序性来提高插入排序的效率
     * 80000随机数排序耗时：13ms/4464
     */
    private static int[] sort(int[] arrays) {
        for (int step = arrays.length / 2; step > 0; step /= 2) {
            for (int i = 1; i < arrays.length; i++) {
                int insertValue = arrays[i];
                int j = i - step;
                while (j >= 0 && insertValue <= arrays[j]) {
                    arrays[j + step] = arrays[j];
                    j -= step;
                }
                if (j < i - step) {
                    arrays[j + step] = insertValue;
                }
            }
        }
        return arrays;
    }

    private static int[] sort2(int[] arrays) {
        for (int step = arrays.length / 2; step > 0; step /= 2) {
            for (int i = step; i < arrays.length; i++) {
                int j = i - step;
                while (j >= 0) {
                    if (arrays[j] > arrays[j + step]) {
                        int temp = arrays[j];
                        arrays[j] = arrays[j + step];
                        arrays[j + step] = temp;
                    }
                    j -= step;
                }
            }
        }
        return arrays;
    }
}
