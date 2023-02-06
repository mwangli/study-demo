package mwang.online.hot100;

/**
 * 33. 搜索旋转排序数组
 * https://leetcode.cn/problems/search-in-rotated-sorted-array/description/
 * <p>
 * 思路：先将数据还原成旋转钱的有序数组，再进行二分查找
 */
public class No33_SearchBinary {

    public static void main(String[] args) {
        int search = search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0);
        System.out.println(search);
    }

    public static int search(int[] nums, int target) {
        // 利用升序规律先找到旋转位置k
        int k = 0;
        while (k < nums.length - 1 && nums[k] < nums[k + 1]) {
            k++;
        }
        // 将数组还原成有序
        int[] newNums = new int[nums.length];
        for (int i = 0, j = k + 1; j <= nums.length - 1; i++, j++) {
            newNums[i] = nums[j];
        }
        for (int i = nums.length - k - 1, j = 0; j <= k; i++, j++) {
            newNums[i] = nums[j];
        }
        // 进行二分查找
        int sortedIndex = binarySearch2(newNums, 0, nums.length, target);
        //  计算出原数组中目标数值的下标
        return sortedIndex == -1 ? -1 : (sortedIndex + k + 1) % nums.length;
    }

    public static int binarySearch(int[] nums, int left, int right, int target) {
        if (left > right) return -1;
        int mid = (left + right) / 2;
        if (target < nums[mid]) {
            return binarySearch(nums, left, mid - 1, target);
        } else if (target > nums[mid]) {
            return binarySearch(nums, mid + 1, right, target);
        } else {
            return mid;
        }
    }

    public static int binarySearch2(int[] nums, int left, int right, int target) {
        int l = left;
        int r = right;
        while (l < r) {
            int mid = (l + r) / 2;
            if (target < nums[mid]) {
                r = mid - 1;
            } else if (target > nums[mid]) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
