package mwang.online;

import java.util.ArrayList;

/**
 * 3. 无重复字符的最长子串 - middle
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/
 */
public class MaxLengthSubString {

    public static void main(String[] args) {
        String str = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(str));
    }

    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            // 出现重复则起始位置后移并重新判断
            if (list.contains(c)) {
                maxLength = Math.max(maxLength, list.size());
                list.remove(0);
                i--;
            } else {
                list.add(c);
            }
        }
        return Math.max(maxLength, list.size());
    }
}
