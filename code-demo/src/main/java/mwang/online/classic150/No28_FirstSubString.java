package mwang.online.classic150;

import org.junit.Assert;
import org.junit.Test;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/3/20 10:12
 * @description: No28_FirstSubString
 */
public class No28_FirstSubString {

    @Test
    public void test() {
        var s1 = "sadbutsad";
        var s2 = "sad";
        var s3 = "leetcode";
        var s4 = "leeto";
        var s5 = "hello";
        var s6 = "ll";
        Assert.assertEquals(strStr(s1, s2), 0);
        Assert.assertEquals(strStr(s3, s4), -1);
        Assert.assertEquals(strStr(s5, s6), 2);
    }

    // O(m*n) O(1)
    public int strStr(String haystack, String needle) {
        // [left,right) is reference to matches characters in haystack.
        int left = 0, right = 0;
        // index is reference to char index in needle.
        int index = 0;
        while (right < haystack.length() && index < needle.length()) {
            // if matched, right index plus 1, continue search until the last one.
            if (haystack.charAt(right) == needle.charAt(index)) {
                right++;
                index++;
            } else {
                // if not matched, skip the current char and reset right and index.
                left++;
                right = left;
                index = 0;
            }
        }
        // left != right, that means haystack have matched some char.
        // index == needle.length(), that means all of chars in needle have matched.
        boolean matched = left != right && index == needle.length();
        return matched ? left : -1;
    }
}
