package com.example.redisdemo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    StringRedisTemplate redisTemplate;

    private final Long count = 0L;

    @RequestMapping("/test")
    public String test() {
        return "访问人数统计：" + redisTemplate.opsForValue().increment("count");
    }


}
