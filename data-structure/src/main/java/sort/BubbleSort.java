package sort;

import java.util.Date;
import java.util.Random;

/**
 * @author mwangli
 * @date 2022/2/17 16:46
 */
public class BubbleSort {

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

    private static int[] sort(int[] arrays) {
        for (int i = 0; i < arrays.length - 1; i++) {
            boolean f = false;
            for (int j = 0; j < arrays.length - 1 - i; j++) {
                if (arrays[j] > arrays[j + 1]) {
                    int temp = arrays[j];
                    arrays[j] = arrays[j + 1];
                    arrays[j + 1] = temp;
                    f = true;
                }
            }
            if (!f) {
                break;
            } else {
                f = false;
            }
        }
        return arrays;
    }
}
