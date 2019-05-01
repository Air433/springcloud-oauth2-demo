package com.example.demo.common.core.annotation;

import java.lang.annotation.*;

/**
 * 系统日志
 * @Author oyg
 * @Date 2018/7/29/17:59
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogAn {

    String value() default "";
}
