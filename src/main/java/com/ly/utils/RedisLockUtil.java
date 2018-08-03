package com.ly.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author : ly.
 * @Date : 2018/5/23.
 */
@Component
public class RedisLockUtil {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public boolean isLock(String lockKey,Integer sec){
        boolean isLock = false;
        try {
            Boolean flag = redisTemplate.opsForValue().setIfAbsent(lockKey, "1");
            if (flag) {
                isLock = true;
            } else {
                redisTemplate.opsForValue().getOperations().expire(lockKey, sec, TimeUnit.SECONDS);
            }
        }catch (Exception e){

        }
        return isLock;
    }

    public void unLock(String lockKey){
        redisTemplate.opsForValue().getOperations().delete(lockKey);
    }
}
