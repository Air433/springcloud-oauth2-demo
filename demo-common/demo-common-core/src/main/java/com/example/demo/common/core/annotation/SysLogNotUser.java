package com.example.demo.common.core.annotation;

import java.lang.annotation.*;

/**
 * @Author oyg
 * @Date 2018/9/2/20:51
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogNotUser {
    String value() default "";
}
