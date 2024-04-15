package mwang.online.classic150;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/4/15 10:08
 * @description: No383_RansomNote
 */
public class No383_RansomNote {


    @Test
    public void test() {

        String a1 = "a";
        String b1 = "b";
        Assert.assertFalse(canConstruct(a1, b1));

        String a2 = "aa";
        String b2 = "ab";
        Assert.assertFalse(canConstruct(a2, b2));

        String a3 = "aa";
        String b3 = "aab";
        Assert.assertTrue(canConstruct(a3, b3));
    }


    public boolean canConstruct(String ransomNote, String magazine) {
        // use map to do wordcount
        final HashMap<Character, Integer> map1 = wordCount(ransomNote);
        final HashMap<Character, Integer> map2 = wordCount(magazine);
        if (map1.size() > map2.size()) return false;
        for (Character key : map1.keySet()) {
            if (map1.get(key) > map2.getOrDefault(key, 0)) return false;
        }
        return true;
    }

    private HashMap<Character, Integer> wordCount(String words) {
        final HashMap<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < words.length(); i++) {
            final char c = words.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }
        return countMap;
    }
}
