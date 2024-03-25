package mwang.online.classic150;

import org.junit.Assert;
import org.junit.Test;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/3/19 09:44
 * @description: No14_LongestCommonPrefix
 */
public class No14_LongestCommonPrefix {

    @Test
    public void test() {
        final String[] case1 = {"flower", "flow", "flight"};
        final String[] case2 = {"dog", "racecar", "car"};
        final String[] case3 = {""};
        final String[] case4 = {"", "b"};
        Assert.assertEquals(longestCommonPrefix(case1), "fl");
        Assert.assertEquals(longestCommonPrefix(case2), "");
        Assert.assertEquals(longestCommonPrefix(case3), "");
        Assert.assertEquals(longestCommonPrefix(case4), "");
    }

    // 纵向查找 O(m*n) O(1)
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) return strs[0];
        var res = "";
        int index = 0;
        // 0 <= strs[i].length <= 200
        int MAX_LENGTH = 200;
        boolean finished = false;
        while (!finished && index < MAX_LENGTH) {
            var preStr = strs[0];
            // start from 0 index to prevent str[0][index] out of boundary
            for (int i = 0; i < strs.length; i++) {
                final String str = strs[i];
                final int length = str.length();
                if (index >= length) {
                    finished = true;
                    break;
                }
                final char curChar = str.charAt(index);
                final char preChar = preStr.charAt(index);
                if (curChar != preChar) {
                    finished = true;
                    break;
                }
            }
            index++;
        }
        res = strs[0].substring(0, index - 1);
        return res;
    }
}
