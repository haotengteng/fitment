package com.zuoan.utils.redis;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * 创建一个Aop拦截器的处理类,用于拦截加了@RedisCacheable的方法
 * Created by Xujy on 2016/3/26.
 */
@Aspect
@Component
public class RedisCacheableForUpdateAop {
    @Autowired
    private RedisTemplate redisTemplate;

    @Around("@annotation(cache)")
    public Object cached(final ProceedingJoinPoint pjp, RedisCacheableForUpdate cache) throws Throwable {

        String key = getCacheKey(pjp, cache);
        ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();

        Object value = valueOper.get(key);    //从缓存获取数据
        if (value != null) {
            redisTemplate.delete(key);
        }
        return pjp.proceed();//跳过缓存,到后端查询数据

    }

    /**
     * 获取缓存的key值
     *
     * @param pjp
     * @param cache
     * @return key
     */
    private String getCacheKey(ProceedingJoinPoint pjp, RedisCacheableForUpdate cache) {
        StringBuilder buf = new StringBuilder();

        Object[] args = pjp.getArgs();

        if (cache.keyMode() == RedisCacheableForUpdate.KeyMode.DEFAULT) {
            try {
                Field field = args[0].getClass().getDeclaredFields()[1];
                field.setAccessible(true);
                buf =  new StringBuilder(field.get(args[0]).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return buf.append("aop").toString();
    }
}
