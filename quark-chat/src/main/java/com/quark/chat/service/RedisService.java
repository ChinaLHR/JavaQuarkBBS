package com.quark.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
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
     * 设置String缓存
     * @param key
     * @param t
     */
    public void cacheString(String key, T t,int time){
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        operations.set(key,t,time, TimeUnit.HOURS);
    }

    /**
     * 获取String缓存并更新
     * @param key
     * @return
     */
    public T getStringAndUpDate(String key,int time){
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        T t = operations.get(key);
        if (t!=null) operations.set(key,t,time, TimeUnit.HOURS);
        return t;
    }

    /**
     * 获取String缓存
     * @param key
     * @return
     */
    public T getString(String key){
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        T t = operations.get(key);
        return t;
    }

    /**
     * 删除String缓存
     * @param key
     */
    public void deleteString(String key){
        redisTemplate.delete(key);
    }

    /**
     * 判断String是否存在键key
     * @param key
     * @return
     */
    public boolean stringHasKey(String key){
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        return redisTemplate.hasKey(key);
    }

    /**
     * 设置Set缓存
     * @param key
     * @param t
     */
    public void cacheSet(String key,T t){
        SetOperations<String,T> opsForSet = redisTemplate.opsForSet();
        opsForSet.add(key,t);
    }

    /**
     * 删除Set缓存
     * @param key
     * @param t
     */
    public void deleteSet(String key,T t){
        SetOperations<String,T> opsForSet = redisTemplate.opsForSet();
        opsForSet.remove(key,t);
    }

    /**
     * 判断Set是否存在value
     * @param key
     * @param t
     * @return
     */
    public boolean setHasValue(String key, T t){
        SetOperations<String,T> opsForSet = redisTemplate.opsForSet();
        return opsForSet.isMember(key, t);
    }


}
