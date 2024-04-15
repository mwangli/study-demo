package mwang.online.classic150;

import org.junit.Test;

import java.util.HashSet;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/3/25 11:04
 * @description: No3_MaxSubString
 */
public class No3_MaxSubString {

    @Test
    public void test() {
        var str1 = "abcabcbb";
        var str2 = "bbbbb";
        var str3 = "pwwkew";
        var str4 = " ";
        assert lengthOfLongestSubstring(str1) == 3;
        assert lengthOfLongestSubstring(str2) == 1;
        assert lengthOfLongestSubstring(str3) == 3;
        assert lengthOfLongestSubstring(str4) == 1;
    }

    // O(n) O(n)
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        int left = 0, right = 0;
        final HashSet<Character> set = new HashSet<>();
        while (left < s.length() && right < s.length()) {
            // right++ try to get max length
            while (right < s.length() && !set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                right++;
            }
            res = Math.max(res, right - left);
            // left++ to next loop try
            if (left < right) {
                set.remove(s.charAt(left));
            }
            left++;
        }
        return res;
    }
}
