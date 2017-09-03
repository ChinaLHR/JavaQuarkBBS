package com.quark.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author LHR
 * Create By 2017/8/31
 */
@Service
public class RedisService<T> {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置缓存
     * @param key
     * @param t
     */
    public void cacheSet(String key, T t,int time){
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        operations.set(key,t,time, TimeUnit.HOURS);
    }

    /**
     * 获取缓存并更新
     * @param key
     * @return
     */
    public T getSetAndUpDate(String key,int time){
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        T t = operations.get(key);
        if (t!=null) operations.set(key,t,time, TimeUnit.HOURS);
        return t;
    }

    /**
     * 获取缓存
     * @param key
     * @return
     */
    public T getSet(String key){
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        T t = operations.get(key);
        return t;
    }

    /**
     * 删除缓存
     * @param key
     */
    public void deleteSet(String key){
        redisTemplate.delete(key);
    }

    /**
     * 判断是否存在键
     * @param key
     * @return
     */
    public boolean hasKey(String key){
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        return redisTemplate.hasKey(key);
    }
}
