package mwang.online.daily;

import java.util.*;

/**
 * 1604. 警告一小时内使用相同员工卡大于等于三次的人
 * https://leetcode.cn/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period/description/
 * 思路：考察对map的使用
 */
public class No1046_AlarmTime {

    public static void main(String[] args) {
        var keyName = new String[]{"b","b","b","b","b","b"};
        var keyTime = new String[]{"00:52","10:59","17:16","00:36","01:26","22:42"};
        List<String> ans = alertNames(keyName, keyTime);
        System.out.println(ans);
    }

    public static List<String> alertNames(String[] keyName, String[] keyTime) {
        // 使用map归类
        var map = new HashMap<String, List<Integer>>();
        for (var i = 0; i < keyName.length; i++) {
            String key = keyName[i];
            String time = keyTime[i];
            List<Integer> list = map.getOrDefault(key, new ArrayList<>());
            list.add(changeTimeToMinutes(time));
            map.put(key, list);
        }
        // 判断是否一小时
        ArrayList<String> ans = new ArrayList<>();
        map.forEach((k, v) -> {
            Collections.sort(v);
            if (inAnHour(v)) {
                ans.add(k);
            }
        });
        // 返回排序结果
        Collections.sort(ans);
        return ans;
    }

    public static boolean inAnHour(List<Integer> times) {
        if (times.size() < 3) {
            return false;
        }
        for (int i = 0; i < times.size() - 2; i++) {
            Integer startTime = times.get(i);
            Integer endTime = times.get(i + 2);
            // end > start 解决跨天问题
            if (endTime > startTime && endTime - startTime <= 60) {
                return true;
            }
        }
        return false;
    }

    public static int changeTimeToMinutes(String time) {
        // 以零时刻作为起点，到现在的分钟数，用于计算差值
        int hour = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
        int minute = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
        return hour * 60 + minute;
    }
}
