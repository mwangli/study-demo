package mwang.online.classic150;

import org.junit.Assert;
import org.junit.Test;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/3/21 09:31
 * @description: No392_IsSubsequence
 */
public class No392_IsSubsequence {

    @Test
    public void test() {
        var s1 = "acb";
        var t1 = "abc cb";
        var s2 = "axc";
        var t2 = "false";
        Assert.assertTrue(isSubsequence(s1, t1));
        Assert.assertFalse(isSubsequence(s2, t2));
    }


    public boolean isSubsequence(String s, String t) {
        int indexS = 0;
        int indexT = 0;
        while (indexS < s.length() && indexT < t.length()) {
            if (s.charAt(indexS) == t.charAt(indexT)) {
                indexS++;
            }
            indexT++;
        }
        return indexS == s.length();
    }
}
