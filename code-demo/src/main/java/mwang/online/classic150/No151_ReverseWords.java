package mwang.online.classic150;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/3/19 10:18
 * @description: No151_ReverseWords
 */
public class No151_ReverseWords {

    @Test
    public void test() {
        var case1 = "the sky is blue";
        var res1 = "blue is sky the";
        var case2 = "  hello world  ";
        var res2 = "world hello";
        var case3 = "a good   example";
        var res3 = "example good a";
        Assert.assertEquals(reverseWords(case1), res1);
        Assert.assertEquals(reverseWords(case2), res2);
        Assert.assertEquals(reverseWords(case3), res3);
    }


    // use API
    public String reverseWords2(String s) {
        s = s.trim();
        final String[] split = s.split("\\s+");
        final List<String> list = Arrays.asList(split);
        Collections.reverse(list);
        final String join = String.join(" ", list);
        System.out.println(join);
        return join;
    }

    // whole reverse and then single word reverse
    public String reverseWords(String s) {
        // step1: reverse all string
        final String reversedStr = new StringBuilder(s.trim()).reverse().toString();
        // step2: reverse single word
        final StringBuilder res = new StringBuilder();
        final StringBuilder temp = new StringBuilder();
        for (int i = 0; i < reversedStr.length(); i++) {
            final char c = reversedStr.charAt(i);
            if (c == ' ') {
                // prevent to write too many space
                if (temp.length() > 0) {
                    res.append(temp.reverse()).append(' ');
                    temp.delete(0, temp.length());
                }
            } else {
                temp.append(c);
            }
        }
        res.append(temp.reverse()).append(' ');
        return res.toString().trim();
    }
}
