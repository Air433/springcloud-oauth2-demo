package com.example.demo.common.core.annotation;

import java.lang.annotation.*;

/**
 * @Author oyg
 * @Date 2018/10/11/19:36
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLock {

    String lockName();

    int expire();

}
