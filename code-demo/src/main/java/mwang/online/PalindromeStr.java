package mwang.online;

public class PalindromeStr {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("cbba"));
//        System.out.printf("babad".substring(0,3));
    }

    public static String longestPalindrome(String s) {
        String res1 = palindrome(s, 1);
        String res2 = palindrome(s, 2);
        return res1.length() > res2.length() ? res1 : res2;
    }

    public static String palindrome(String s, int type) {
        int maxLength = 0;
        int start = 0, end = 0;
        // 选择中间位置
        for (int i = 0; i < s.length(); i++) {
            int left = i;
            // 区分中间一个字符和两个字符的情况
            int right = type == 1 ? i : i + 1;
            // 向两侧扩展直至不满足回文
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            left++;
            right--;
            // 保存最大长度
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                start = left;
                end = right;
            }
        }
        return s.substring(start, end + 1);
    }
}
