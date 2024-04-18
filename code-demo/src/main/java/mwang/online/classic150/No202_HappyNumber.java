package mwang.online.classic150;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/4/18 10:20
 * @description: No202_HappyNumber
 */
public class No202_HappyNumber {


    @Test
    public void test() {
        assert isHappy(19);
        assert !isHappy(2);
    }

    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        while (n != 1) {
            int nextNumber = getNextNumber(n);
            if (set.contains(nextNumber)) {
                return false;
            } else {
                set.add(nextNumber);
            }
            n = nextNumber;
        }
        return true;
    }

    private int getNextNumber(int n) {
        int total = 0;
        while (n != 0) {
            int i = n % 10;
            total += i * i;
            n /= 10;
        }
        return total;
    }
}
