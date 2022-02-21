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
//        System.out.println("结果：" + Arrays.toString(sort));
    }

    /**
     * 从无序列表逐一将数字按照顺序插入到有序列表中的指定位置
     * 值执行n-1趟排序，每趟排序执行n次比较，移动n次数据
     * 80000随机数排序耗时：449ms
     */
    private static int[] sort(int[] arrays) {
        for (int i = 1; i < arrays.length; i++) {
            int insertNum = arrays[i];
            int j = i - 1;
            while (j >= 0 && insertNum <= arrays[j]) {
                arrays[j + 1] = arrays[j];
                j--;
            }
            if (j < i + 1) {
                arrays[j + 1] = insertNum;
            }
        }
        return arrays;
    }
}
