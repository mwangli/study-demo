package mwang.online;

public class MaxAreaWater {

    public static void main(String[] args) {
        int[] heights = {1, 8, 100, 2, 100, 4, 8, 3, 7};
        System.out.println(maxArea3(heights));
    }

    public static int maxArea(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i; j < height.length; j++) {
                int area = (j - i) * Math.min(height[i], height[j]);
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    public static int maxArea2(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        //  移动数值较小的一侧往中间
        while (left <= right) {
            int h = Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, (right - left) * h);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    public static int maxArea3(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        //  只能移动较小的边界来获取更大面积，相当于缩减问题规模
        //  getMax(height[n]) = getMax(height[n-1])
        //  height[n-1] 为 height[n]舍去较小边界
        return getMax(height, left, right, maxArea);
    }

    private static int getMax(int[] height, int left, int right, int maxArea) {
        // 计算面积
        int area = (right - left) * Math.min(height[left], height[right]);
        maxArea = Math.max(maxArea, area);
        // 递归终止条件
        if (left == right) return maxArea;
        //  开始递归
        if (height[left] < height[right]) {
            return getMax(height, left + 1, right, maxArea);
        } else {
            return getMax(height, left, right - 1, maxArea);
        }
    }
}
