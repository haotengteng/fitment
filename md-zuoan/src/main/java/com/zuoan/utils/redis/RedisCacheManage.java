package com.zuoan.utils.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 用于增加缓存
 * Created by haotengteng on 2016/3/27.
 */
@Component
public class RedisCacheManage {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据key获取value
     *
     * @param key
     * @return
     */
    public Object getValue(String key) {
        ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();
        Object value = valueOper.get(key);
        return value;
    }

    /**
     * 增加缓存
     *
     * @param key
     * @param t
     * @param <T>
     */
    public <T> void addCache(String key, T t) {
        ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();
        valueOper.set(key, t);
    }

    /**
     * 增加缓存带超时时间
     *
     * @param key
     * @param t
     * @param <T>
     */
    public <T> void addCache(String key, T t, int time) {
        ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();
        valueOper.set(key, t, time, TimeUnit.SECONDS);
    }
}
