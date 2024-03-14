package mwang.online.hot100;

/**
 * 55. 跳跃游戏
 * https://leetcode.cn/problems/jump-game/description/
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 * <p>
 * 思路：1.递归回溯(超时) 本质是在穷举
 * 2.贪心算法 维护一个最远可到达位置， 如果当前位置小于等于最远可到达位置，说明当前位置可达，
 * 如果当前位置的最远可到达位置更远， 将最远可到达位置更新，为当前位置+当前最大可跳跃距离，否则不更新
 * 如果当前位置小于等于最远可到达位置说明当前位置不可达，则不更新
 * 如果最远可达位置>=终点位置，则终点可达
 */
public class No55_JumpGame {

    public boolean flag = false;

    public static void main(String[] args) {
        int[] ints = {2, 2, 1, 1, 4};
        No55_JumpGame o = new No55_JumpGame();
        System.out.println(o.canJump(ints));
    }


    // 贪心算法
    public boolean canJump(int[] nums) {
//        dfs(nums, 0);
        var maxIndex = 0;
        var n = nums.length;
        for (var i = 0; i < n; i++) {
            // 当前位置可达
            if (i <= maxIndex) {
                // 取当前位置+当前最大可跳跃距离 或者 取之前的
                // 经过当前位置不能调到更远距离，则不走当前位置
                maxIndex = Math.max(i + nums[i], maxIndex);
            }
            // 终点可达
            if (maxIndex >= n - 1) {
                return true;
            }
        }
        return false;
    }

    public void dfs(int[] nums, int index) {
        if (index == nums.length - 1) {
            flag = true;
            return;
        }
        int stepNum = nums[index];
        // 遍历在当前位置可尝试跳跃的长度
        for (int i = stepNum; i >= 1; i--) {
            if (index + i < nums.length) {
                dfs(nums, index + i);
            }
        }
    }
}
