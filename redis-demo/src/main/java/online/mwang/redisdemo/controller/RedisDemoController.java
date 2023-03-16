package online.mwang.redisdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/14 13:42
 * @description: RedisDemoController
 */
@RestController
public class RedisDemoController {

    @Autowired
    StringRedisTemplate redisTemplate;

    @RequestMapping("/test")
    public String test() {
        return "v1.1 : 当前访问人数" + redisTemplate.opsForValue().increment("count");
    }
}
