package mwang.online.classic150;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/3/20 09:27
 * @description: No6_ZigzagConversion
 */
public class No6_ZigzagConversion {

    @Test
    public void test() {
        var str1 = "PAYPALISHIRING";
        var num1 = 3;
        var res1 = "PAHNAPLSIIGYIR";
        var str2 = "PAYPALISHIRING";
        var num2 = 4;
        var res2 = "PINALSIGYAHRPI";
        var str3 = "A";
        var num3 = 1;
        var res3 = "A";
        Assert.assertEquals(convert(str1, num1), res1);
        Assert.assertEquals(convert(str2, num2), res2);
        Assert.assertEquals(convert(str3, num3), res3);
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        final ArrayList<StringBuilder> stringBuilders = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            // ith StringBuilder is used to save ith string
            stringBuilders.add(new StringBuilder());
        }
        // flat the string to compute current character is located witch StringBuilder
        for (int i = 0; i < s.length(); i++) {
            // cycle index =  i % (2* (numRows-1))
            int index = i % (2 * (numRows - 1));
            int maxIndex = numRows - 1;
            if (index > maxIndex) {
                // go back steps
                final int steps = index - maxIndex;
                index = maxIndex - steps;
            }
            stringBuilders.get(index).append(s.charAt(i));
        }
        return String.join("", stringBuilders);
    }
}
