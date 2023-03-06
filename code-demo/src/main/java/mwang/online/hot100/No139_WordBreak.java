package mwang.online.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/6 09:17
 * @description: No139_WordBreak
 * 139. 单词拆分
 * 思路：动态规划
 * dp[i] = dp[j] && contains(s[j,i-1])
 */
public class No139_WordBreak {

    public static void main(String[] args) {
        ArrayList<String> wordDic = new ArrayList<>();
        wordDic.add("aa");
        wordDic.add("aaa");
        String s = "aaaaa";
        System.out.println(wordBreak(s, wordDic));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                final String substring = s.substring(j, i);
                if (dp[j] && wordDict.contains(substring)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
