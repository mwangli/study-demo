package mwang.online.daily;

import java.util.*;

/**
 * 2325. 解密消息
 * 思路：利用Map结构形成对照表
 * <p>
 * 总结HashMap无序，TreeMap, LinkHashMap有序
 */
public class No2325_DecodeMessage {

    public static void main(String[] args) {
        var key = "the quick brown fox jumps over the lazy dog";
        var message = "vkbs bs t suepuv";
        String decodeMessage = decodeMessage(key, message);
        System.out.println(decodeMessage);
        var list = List.of("Java", "Python", "C"); //不可变集合
        var copy = List.copyOf(list); //copyOf判断是否是不可变集合类型，如果是直接返回
        System.out.println(list == copy); // true
//        var list = new ArrayList<String>(); // 这里返回正常的集合
//        var copy = List.copyOf(list); // 这里返回一个不可变集合
//        System.out.println(list == copy); // false
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
        map.forEach((k, v) -> System.out.println(k + ":" + v));
        // 解密密文
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            stringBuilder.append(map.getOrDefault(c, c));
        }
        return stringBuilder.toString();
    }
}
