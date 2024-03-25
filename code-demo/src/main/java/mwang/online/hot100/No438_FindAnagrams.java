package mwang.online.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/4/27 09:52
 * @description: No438_FindAnagrams
 */
public class No438_FindAnagrams {


    public static void main(String[] args) {
        System.out.println(findAnagrams("abab", "ab"));
    }

    public static List<Integer> findAnagrams(String s, String p) {
        final ArrayList<Integer> indexes = new ArrayList<>();
        // 统计S中各个字母出现的次数
        final int[] countP = new int[26];
        for (int i = 0; i < p.length(); i++) {
            countP[p.charAt(i) - 'a']++;
        }
        // 滑动窗口统计S
        final int[] countS = new int[26];
        for (int i = 0; i <= s.length() - p.length(); i++) {
            Arrays.fill(countS, 0);
            for (int j = i; j < i + p.length(); j++) {
                countS[s.charAt(j) - 'a']++;
            }
            if (Arrays.equals(countP, countS)) {
                indexes.add(i);
            }
        }
        return indexes;
    }
}
