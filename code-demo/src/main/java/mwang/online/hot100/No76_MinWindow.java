package mwang.online.hot100;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/5/16 13:57
 * @description: No76_MinWindow
 */
public class No76_MinWindow {

    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
//        String s = "XXXXAXXBCXXXX", t = "ABC";
        System.out.println(minWindow(s, t));
    }

    public static String minWindow(String s, String t) {
        int left = 0, right = 0;
        // 统计t中字母出现的次数
        final HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            final char c = t.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        s = preHandle(s, map);
        System.out.println(s);
        // 扩展s的右区间
        int l = 0, r = 0, minLength = s.length() + 1;
        final HashMap<Character, Integer> map2 = new HashMap<>();
        while (right < s.length()) {
            final char c = s.charAt(right);
            if (map.containsKey(c)) {
                map2.put(c, map2.getOrDefault(c, 0) + 1);
            }
            right++;
            // 在覆盖的情况下收缩左区间
            while (left < s.length() && check(map2, map)) {
                final char c2 = s.charAt(left);
                if (map.containsKey(c2)) {
                    map2.put(c2, map2.get(c2) - 1);
                }
                left++;
                // 更新最小区间
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    l = left;
                    r = right;
                }
            }
        }
        return l - 1 < 0 ? "" : s.substring(l - 1, r);
    }

    // 判断集合覆盖，集合1中的次数不小于集合2中的次数
    private static boolean check(HashMap<Character, Integer> map1, HashMap<Character, Integer> map2) {
        AtomicBoolean f = new AtomicBoolean(true);
        map2.forEach((k, v) -> {
            if (!map1.containsKey(k) || map1.get(k) < v) f.set(false);
        });
        return f.get();
    }

    private static String preHandle(String s, HashMap<Character, Integer> map) {
        int left = 0, right = s.length() - 1;
        while (!map.containsKey(s.charAt(left))) left++;
        while (!map.containsKey(s.charAt(right))) right--;
        if (left - 1 < 0) left++;
        if (right == s.length() - 1) right++;
        return s.substring(left - 1, right);
    }
}
