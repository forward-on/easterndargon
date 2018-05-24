package com.ly.utils;

/**
 * @author : ly.
 * @Date : 2018/5/23.
 */
public class RedisLockUtil {

//    RedisTemplate

    /*public static boolean isLock(String lockKey,Integer sec,ICacheClient cacheClient){
        boolean isLock = false;
        try {
            Long nx = cacheClient.setnx(lockKey, "1");
            if (nx == 0) {
                isLock = true;
            } else {
                cacheClient.expire(lockKey, sec);
            }
        }catch (Exception e){

        }
        return isLock;
    }

    public static void unLock(String lockKey,ICacheClient cacheClient){
        cacheClient.del(lockKey);
    }
*/
}
