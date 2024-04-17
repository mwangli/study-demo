package mwang.online.classic150;

import org.junit.Test;

import java.util.HashMap;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/4/17 10:37
 * @description: No290_WordPattern
 */
public class No290_WordPattern {

    @Test
    public void test() {
        String pattern1 = "abba", s1 = "dog cat cat dog";
        assert wordPattern(pattern1, s1);
        String pattern2 = "abba", s2 = "dog cat cat fish";
        assert !wordPattern(pattern2, s2);
    }


    public boolean wordPattern(String pattern, String s) {
        HashMap<Object, Object> map1 = new HashMap<>(16);
        HashMap<String, Character> map2 = new HashMap<>(16);
        String[] split = s.split(" ");
        if (pattern.length() != split.length) return false;
        for (int i = 0; i < pattern.length(); i++) {
            final char c = pattern.charAt(i);
            final String str = split[i];
            if (map1.containsKey(c)) {
                if (!map1.get(c).equals(str)) return false;
            } else {
                map1.put(c, str);
            }
            if (map2.containsKey(str)) {
                if (!map2.get(str).equals(c)) return false;
            } else {
                map2.put(str, c);
            }
        }
        return true;
    }
}
