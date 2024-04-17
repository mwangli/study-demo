package mwang.online.classic150;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/4/17 10:07
 * @description: No205_IsomorphicString
 */
public class No205_IsomorphicString {

    @Test
    public void test() {
        var s1 = "egg";
        var t1 = "add";
        var s2 = "badc";
        var t2 = "baba";
        assert isIsomorphic(s1, t1);
        assert !isIsomorphic(s2, t2);
    }

    public boolean isIsomorphic(String s, String t) {
        int length = s.length();
        final HashMap<Character, Character> map1 = new HashMap<>(16);
        final HashMap<Character, Character> map2 = new HashMap<>(16);
        for (int i = 0; i < length; i++) {
            final char sChar = s.charAt(i);
            final char tChar = t.charAt(i);
            if (map1.containsKey(sChar)) {
                if (map1.get(sChar) != tChar) return false;
            } else {
                map1.put(sChar, tChar);
            }
            if (map2.containsKey(tChar)) {
                if (map2.get(tChar) != sChar) return false;
            } else {
                map2.put(tChar, sChar);
            }
        }
        return true;
    }
}
