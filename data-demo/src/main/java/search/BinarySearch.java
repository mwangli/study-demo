package search;

import java.util.Date;

/**
 * @author mwangli
 * @date 2022/2/24 16:55
 */
public class BinarySearch {

    private static int count = 0;

    public static void main(String[] args) {
        int[] ints = new int[80000];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = i + 1;
        }
        long start = new Date().getTime();
        int index = search2(ints, 10000, 0, ints.length - 1);
        long end = new Date().getTime();
        System.out.println("耗时：" + (count));
        System.out.println("结果：" + ints[index]);
    }

    private static int search(int[] arr, int value, int left, int right) {
        count++;
        if (left > right || value < arr[left] || value > arr[arr.length - 1]) return -1;
        long mid = left + (long) (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
//        int mid = left+ (right-left)*  1/2;
        int midIndex = (int) mid;
        if (value > arr[midIndex]) {
            return search(arr, value, midIndex + 1, right);
        } else if (value < arr[midIndex]) {
            return search(arr, value, left, midIndex - 1);
        } else {
            return midIndex;
        }
    }

    private static int search2(int[] arr, int value, int left, int right) {
        int l = left;
        int r = right;
        while (l < r) {
            count++;
            int mid = (l + r) / 2;
            if (value == arr[mid]) {
                return mid;
            } else if (value > arr[mid]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }
}
