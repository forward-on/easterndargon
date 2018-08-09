package com.ly.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author : ly.
 * @Date : 2018/5/23.
 */
@Component
public class RedisLockUtil {

    private Logger logger = LoggerFactory.getLogger(RedisLockUtil.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public boolean isLock(String lockKey, Integer sec) {
        /**
         * 那么这段代码问题在哪里？
         *         1. 由于是客户端自己生成过期时间，所以需要强制要求分布式下每个客户端的时间必须同步。
         *         2. 当锁过期的时候，如果多个客户端同时执行 getAndSet() 方法，那么虽然最终只有一个客户端可以加锁，但是这个客户端的锁的过期时间可能被其他客户端覆盖。
         *         3. 锁不具备拥有者标识，即任何客户端都可以解锁。
         */
        String expireStr = (System.currentTimeMillis() + sec*1000) + "";

        //如果锁不存在，加锁成功，返回
        Boolean flag = redisTemplate.opsForValue().setIfAbsent(lockKey, expireStr);
        if (flag) {
            return true;
        }

        //如果锁存在，获取过期时间
        String currValue = redisTemplate.opsForValue().get(lockKey);
        if (StringUtils.isNotEmpty(currValue) && Long.parseLong(currValue) < System.currentTimeMillis()) {
            //锁已经过期，获取上一个锁的过期时间，并设置现在锁的过期时间
            String oldValue = redisTemplate.opsForValue().getAndSet(lockKey, expireStr);
            return StringUtils.isNotEmpty(oldValue) && currValue.equals(oldValue);
        }

        return false;

        /**
         * 正确代码待续
         */
    }

    public void unLock(String lockKey, String value) {
        try {
            String current = redisTemplate.opsForValue().get(lockKey);
            if (StringUtils.isNotEmpty(current) && current.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(lockKey);
            }
        } catch (Exception e) {
            logger.error("分布式锁解锁失败,e={}", e);
        }
    }
}
