package mwang.online.hot100;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/5/6 10:32
 * @description: No647_CountSubString
 */
public class No647_CountSubString {

    public static void main(String[] args) {
        String str = "abababa";
        System.out.println(countSubstrings(str));
    }


    public static int countSubstrings(String s) {
        // 枚举所有的回文中心
        int count = 0;
        for (int i = 0; i < 2 * s.length() - 1; i++) {
            int l = i / 2;
            int r = (i + 1) / 2;
            // 回文扩展
            while (l >= 0 && r <= s.length() - 1 && s.charAt(l) == s.charAt(r)) {
                count++;
                l--;
                r++;
            }
        }
        return count;
    }
}
