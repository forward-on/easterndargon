package com.ly.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Date 2018-08-02 11:15
 * @Author ly
 */@Component
public class RedisClient {

     @Autowired
    private RedisTemplate redisTemplate;

     public void getClient(){
         ValueOperations valueOperations = redisTemplate.opsForValue();

     }

}
