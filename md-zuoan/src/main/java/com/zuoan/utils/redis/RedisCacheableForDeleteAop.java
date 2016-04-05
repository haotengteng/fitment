package com.zuoan.utils.redis;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/4/1.
 */
@Aspect
@Component
public class RedisCacheableForDeleteAop {
    @Autowired
    private RedisTemplate redisTemplate;

    @Around("@annotation(cache)")
    public Object cached(final ProceedingJoinPoint pjp, RedisCacheableForDelete cache) throws Throwable {

        String key = getCacheKey(pjp, cache);
        ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();

        Object value = valueOper.get(key);    //从缓存获取数据
        if (value != null) {
            redisTemplate.delete(key);
        }
        valueOper.get(key);
        return pjp.proceed();//跳过缓存,到后端查询数据

    }

    /**
     * 获取缓存的key值
     *
     * @param pjp
     * @param cache
     * @return key
     */
    private String getCacheKey(ProceedingJoinPoint pjp, RedisCacheableForDelete cache) {
        StringBuilder buf = new StringBuilder();

        Object[] args = pjp.getArgs();

        if (cache.keyMode() == RedisCacheableForDelete.KeyMode.DEFAULT) {
            buf = new StringBuilder(args[0].toString());
        }
        return buf.append("aop").toString();
    }
}
