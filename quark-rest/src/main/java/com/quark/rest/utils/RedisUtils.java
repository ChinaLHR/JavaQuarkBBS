package com.quark.rest.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

import static com.quark.rest.utils.Constants.REDIS_TIME;
import static com.quark.rest.utils.Constants.REDIS_USER_KEY;

/**
 * @Author LHR
 * Create By 2017/8/21
 */
public class RedisUtils<T> {

    private RedisTemplate redisTemplate;

    public RedisUtils(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 设置缓存
     * @param key
     * @param t
     */
    public void CacheSet(String key, T t){
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        operations.set(REDIS_USER_KEY+key,t,REDIS_TIME, TimeUnit.DAYS);
    }

    /**
     * 获取缓存
     * @param key
     * @return
     */
    public T getSet(String key){
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        T t = operations.get(REDIS_USER_KEY + key);
        if (t!=null) operations.set(REDIS_USER_KEY+key,t,REDIS_TIME, TimeUnit.DAYS);
        return t;
    }

    public void deleteSet(String key){
        redisTemplate.delete(REDIS_USER_KEY+key);
    }

    /**
     * 判断是否存在键
     * @param key
     * @return
     */
    public boolean hasKey(String key){
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        return redisTemplate.hasKey(REDIS_USER_KEY+key);
    }

    //TODO 添加对Redis数量的检查，达到一定数量时移除令牌
}
