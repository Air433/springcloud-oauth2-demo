package com.example.demo.common.core.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @Author oyg
 * @Date 2018/11/4/19:37
 */
@Aspect
@Component
public class AspectByTwo implements Ordered {

    @Pointcut("@annotation(com.example.demo.common.core.annotation.TestAspect)")
    public void testAspect(){

    }

    @Around("testAspect()")
    public Object aroundTestAspect(ProceedingJoinPoint point) throws Throwable {
        System.err.println("---------切面2");
        Object proceed = null;
        try {
             proceed = point.proceed();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }

        return proceed;
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
