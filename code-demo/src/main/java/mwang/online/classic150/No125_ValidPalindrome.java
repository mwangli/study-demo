package mwang.online.classic150;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/3/13 09:37
 * @description: No125_ValidPalindrome
 */
public class No125_ValidPalindrome {

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
//        String s = "a.";
        final boolean res = new No125_ValidPalindrome().isPalindrome(s);
        System.out.println(res);

    }

    public boolean isPalindrome(String s) {
        s= s.toLowerCase();
        final int n = s.length();
        if (n <= 1) return true;
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            while (left < right && !isAlphanumeric(s.charAt(left))) left++;
            while (left < right && !isAlphanumeric(s.charAt(right))) right--;
            if (left < right) {
                if (s.charAt(left) != s.charAt(right)) return false;
                left++;
                right--;
            }
        }
        return true;
    }


    public boolean isPalindrome2(String s) {
        s = removeInvalidChars(s).toString();
        final int n = s.length();
        if (n <= 1) return true;
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome3(String s) {
        final StringBuilder sb = removeInvalidChars(s);
        final String forwardStr = sb.toString();
        final String backwardStr = sb.reverse().toString();
        return forwardStr.equals(backwardStr);
    }


    private StringBuilder removeInvalidChars(String str) {
        final StringBuilder stringBuilder = new StringBuilder();
        final String s = str.toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            if (isAlphanumeric(c)) {
                stringBuilder.append(c);
            }
        }
        return stringBuilder;
    }

    private boolean isAlphanumeric(char c) {
        return c >= '0' && c <= '9' || c >= 'a' && c <= 'z';
    }
}
