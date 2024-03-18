package mwang.online.classic150;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/3/15 17:12
 * @description: No13_RomanToInt
 */
public class No13_RomanToInt {

    @Test
    public void test() {
        String s1 = "LVIII";
        String s2 = "MCMXCIV";
        String s3 = "IV";
        Assert.assertEquals(romanToInt(s1), 58);
        Assert.assertEquals(romanToInt(s2), 1994);
        Assert.assertEquals(romanToInt(s3), 4);
    }

    //Symbol       Value
    //I             1
    //V             5
    //X             10
    //L             50
    //C             100
    //D             500
    //M             1000
    public int romanToInt(String s) {
        final Stack<Character> charStack = new Stack<>();
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            final char curChar = s.charAt(i);
            // when stack is empty
            if (charStack.empty()) {
                charStack.push(curChar);
                continue;
            }
            final Character preChar = charStack.peek();
            int curInt = charToInt(curChar);
            int preInt = charToInt(preChar);
            // if current integer is larger than previous integer, pop all smaller integer and decrease them.
            if (curInt > preInt) {
                while (!charStack.isEmpty() && charToInt(charStack.peek()) < curInt) {
                    curInt -= charToInt(charStack.pop());
                }
                res += curInt;
            } else {
                charStack.push(curChar);
            }
        }
        // add remains chars
        while (!charStack.isEmpty()) {
            res += charToInt(charStack.pop());
        }
        return res;
    }

    private int charToInt(char c) {
        if (c == 'I') return 1;
        if (c == 'V') return 5;
        if (c == 'X') return 10;
        if (c == 'L') return 50;
        if (c == 'C') return 100;
        if (c == 'D') return 500;
        if (c == 'M') return 1000;
        return 0;
    }
}
