package mwang.online.daily;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 2325. 解密消息
 * 思路：利用Map结构形成对照表
 *
 * 总结HashMap无序，TreeMap, LinkHashMap有序
 */
public class No2325_DecodeMessage {

    public static void main(String[] args) {
        var key = "the quick brown fox jumps over the lazy dog";
        var message = "vkbs bs t suepuv";
        String decodeMessage = decodeMessage(key, message);
        System.out.println(decodeMessage);
    }

    public static String decodeMessage(String key, String message) {
        // 构建字典表
        HashMap<Character, Character> map = new LinkedHashMap<>();
        int count = 0;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (c != ' ' && !map.containsKey(c)) {
                map.put(c, (char) ('a' + count++));
            }
        }
        // 查看字典表
//        for (Map.Entry<Character, Character> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + ":" + entry.getValue());
//        }
        // 解密密文
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            stringBuilder.append(map.getOrDefault(c, c));
        }
        return stringBuilder.toString();
    }
}
