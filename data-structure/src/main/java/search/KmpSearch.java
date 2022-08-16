package search;

import java.util.Arrays;

/**
 * @author mwangli
 * @date 2022/8/15 10:40
 */
public class KmpSearch {

    public static void main(String[] args) {
        String s1 = "ABCDABDAC";
        String s2 = "ABD";
        System.out.println(kmp(s1, s2));
    }

    public static int kmp(String s1, String s2) {
        int i = 0, j = 0;
        int[] next = getNext(s2);
        while (i < s1.length() && j < s2.length()) {
            if (j == -1 || s1.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == s2.length()) {
            return i - j;
        } else {
            return -1;
        }
    }

    public static int[] getNext(String s2) {
        int[] next = new int[s2.length()];
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < s2.length() - 1) {
            if (k == -1 || s2.charAt(j) == s2.charAt(k)) {
                // 当两个字符相同时，就跳过
                if (s2.charAt(++j) == s2.charAt(++k)) {
                    next[j] = next[k];
                } else {
                    next[j] = k;
                }
            } else {
                k = next[k];
            }
        }
        return next;
    }
}
