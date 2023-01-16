package mwang.online;

import java.util.*;

/**
 * 17. 电话号码的字母组合 - middle(递归回溯）
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/description/
 */
public class PhoneNumbers {

    public static final HashMap<String, List<String>> stringMap = new HashMap<>();

    static {
        stringMap.put("2", Arrays.asList("a", "b", "c"));
        stringMap.put("3", Arrays.asList("d", "e", "f"));
        stringMap.put("4", Arrays.asList("g", "h", "i"));
        stringMap.put("5", Arrays.asList("j", "k", "l"));
        stringMap.put("6", Arrays.asList("m", "n", "o"));
        stringMap.put("7", Arrays.asList("p", "q", "r", "s"));
        stringMap.put("8", Arrays.asList("t", "u", "v"));
        stringMap.put("9", Arrays.asList("w", "x", "y", "z"));
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("2345"));
    }

    public static List<String> letterCombinations(String digits) {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<String> result = new ArrayList<>();
        if (digits.length() == 0) return result;
        dfs(digits, 0, result, stringBuilder);
        return result;
    }

    public static void dfs(String digits, int index, List<String> ans, StringBuilder stringBuilder) {
        // 递归终止条件
        if (index == digits.length()) {
            ans.add(stringBuilder.toString());
            return;
        }
        // 递归回溯
        List<String> letters = stringMap.get(String.valueOf(digits.charAt(index)));
        for (String letter : letters) {
            stringBuilder.append(letter);
            dfs(digits, index + 1, ans, stringBuilder);
            // 撤销当前字符
            stringBuilder.deleteCharAt(index);
        }
    }
}
