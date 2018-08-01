package com.ly.redis;

import redis.clients.jedis.Jedis;

/**
 * @author : ly.
 * @Date : 2018/5/23.
 */
public class RedisTest {

    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis();
        String key = jedis.get("key");
        System.out.println(key);

        jedis.set("haha", "ahah");
        System.out.println(jedis.get("haha"));

        jedis.setex("what", 2, "here");
        System.out.println(jedis.ttl("what"));
        Thread.sleep(3000);
        System.out.println(jedis.ttl("what"));

    }

}
