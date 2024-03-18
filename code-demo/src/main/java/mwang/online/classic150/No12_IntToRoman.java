package mwang.online.classic150;

import org.junit.Assert;
import org.junit.Test;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/3/18 10:30
 * @description: No12_IntToRoman
 */
public class No12_IntToRoman {

    @Test
    public void test() {
        String s1 = "LVIII";
        String s2 = "MCMXCIV";
        String s3 = "IV";
        String s4 = "LX";
        Assert.assertEquals(intToRoman(58), s1);
        Assert.assertEquals(intToRoman(1994), s2);
        Assert.assertEquals(intToRoman(4), s3);
        Assert.assertEquals(intToRoman(60), s4);
    }


    public String intToRoman(int num) {
        final StringBuilder res = new StringBuilder();
        final int numM = num / 1000;
        buildChar(res, 'M', numM);
        num %= 1000;
        // if remains count is 9, skip 5
        int numC = num / 100;
        if (numC != 9) {
            final int numD = num / 500;
            buildChar(res, 'D', numD);
            num %= 500;
        }
        numC = num / 100;
        buildChar(res, 'C', numC);
        num %= 100;
        int numX = num / 10;
        if (numX != 9) {
            final int numL = num / 50;
            buildChar(res, 'L', numL);
            num %= 50;
        }
        numX = num / 10;
        buildChar(res, 'X', numX);
        num %= 10;
        if (num != 9) {
            final int numV = num / 5;
            buildChar(res, 'V', numV);
            num %= 5;
        }
        buildChar(res, 'I', num);
        return res.toString();
    }

    private void buildChar(StringBuilder sb, char c, int count) {
        // if char count <= 3, use increase
        if (count <= 3) {
            for (int i = 0; i < count; i++) {
                sb.append(c);
            }
        }
        // if count ==4, use decrease
        if (count == 4) {
            if (c == 'I') sb.append('I').append('V');
            if (c == 'X') sb.append('X').append('L');
            if (c == 'C') sb.append('C').append('D');
        }
        // if count == 9, use decrease and skip 5
        if (count == 9) {
            if (c == 'I') sb.append('I').append('X');
            if (c == 'X') sb.append('X').append('C');
            if (c == 'C') sb.append('C').append('M');
        }
    }
}
