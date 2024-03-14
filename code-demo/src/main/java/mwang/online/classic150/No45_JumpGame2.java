package mwang.online.classic150;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/3/14 10:31
 * @description: No45_JumpGame2
 */
public class No45_JumpGame2 {

    public static void main(String[] args) {
        final int[] ints = {2, 3, 0, 1, 4};
        final int jump = new No45_JumpGame2().jump(ints);
        System.out.println();
    }

    // 使用贪心算法，尽可能往远处跳
    public int jump(int[] nums) {
        int n = nums.length;
        int step = 0;
        int end = 0;
        int maxIndex = 0;
        for (int i = 0; i < n-1; i++) {
            maxIndex = Math.max(maxIndex, i + nums[i]);
            if (i == end) {
                step++;
                end = maxIndex;
            }
        }
        return step;
    }
}
