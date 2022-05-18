package online.mwang.flume.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mwangli
 * @date 2022/4/24 9:43
 */
public class TimestampInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        //  获取事件的值并转换成UTF_8的格式
        byte[] body = event.getBody();
        String log = new String(body, StandardCharsets.UTF_8);

        //  将获取的字符串转换成对象
        JSONObject jsonObject = JSON.parseObject(log);
        //  获取对象中key值为"ts"的字段
        if (jsonObject.containsKey("ts")){
            String ts = jsonObject.getString("ts");
            event.getHeaders().put("timestamp",ts);
        }
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        return list.stream().peek(this::intercept).collect(Collectors.toList());
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new TimestampInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
