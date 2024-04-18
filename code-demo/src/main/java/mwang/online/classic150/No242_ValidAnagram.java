package mwang.online.classic150;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/4/18 09:54
 * @description: No242_ValidAnagram
 */
public class No242_ValidAnagram {

    @Test
    public void test() {
        String s = "anagram", t = "nagaram";
        assert isAnagram(s, t);
    }

    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> map1 = wordCount(s);
        Map<Character, Integer> map2 = wordCount(t);
        if (map1.size() != map2.size()) return false;
        for (Character key:  map1.keySet()){
            if (!map1.get(key).equals(map2.get(key))){
                return false;
            }
        }
        return true;
    }

    private Map<Character, Integer> wordCount(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }
}
