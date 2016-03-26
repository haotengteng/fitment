package com.zuoan.utils.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * redis 的缓存注解类
 * Created by Xujy on 2016/3/26.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RedisCacheable {
    enum KeyMode{
        DEFAULT,    //只有加了@CacheKey的参数,才加入key后缀中
        BASIC,      //只有基本类型参数,才加入key后缀中,如:String,Integer,Long,Short,Boolean
        ALL;        //所有参数都加入key后缀
    }

    String key() default "";     //缓存key
    KeyMode keyMode() default KeyMode.DEFAULT;       //key的后缀模式
    int expire() default 0;      //缓存多少秒,默认无限期
}
