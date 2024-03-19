package mwang.online.classic150;

import org.junit.Assert;
import org.junit.Test;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/3/19 09:35
 * @description: No58_LengthOfLastWord
 */
public class No58_LengthOfLastWord {

    @Test
    public void test() {
        var s1 = "Hello World";
        var s2 = "   fly me   to   the moon  ";
        var s3 = "luffy is still joyboy";
        Assert.assertEquals(lengthOfLastWord(s1),5);
        Assert.assertEquals(lengthOfLastWord(s2),4);
        Assert.assertEquals(lengthOfLastWord(s3),6);
    }

    public int lengthOfLastWord(String s) {
        // from last character to first character search
        s = s.trim();
        int n = s.length();
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            final char c = s.charAt(i);
            if (c == ' ') break;
            count++;
        }
        return count;
    }
}
