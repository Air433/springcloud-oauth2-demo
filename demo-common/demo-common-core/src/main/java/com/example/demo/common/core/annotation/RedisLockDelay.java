package com.example.demo.common.core.annotation;

import java.lang.annotation.*;

/**
 * @Author oyg
 * @Date 2018/10/13/14:31
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLockDelay {
    String lockName();
}
