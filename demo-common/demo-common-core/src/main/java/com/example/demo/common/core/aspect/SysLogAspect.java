package com.example.demo.common.core.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @Author oyg
 * @Date 2018/7/29/18:05
 */
@Aspect
@Component
public class SysLogAspect implements Ordered {

    @Pointcut("@annotation(com.example.demo.common.core.annotation.TestAspect)")
    public void testAspect(){

    }

    @Pointcut("@annotation(com.example.demo.common.core.annotation.SysLogAn)")
    public void logPointCut(){

    }

    @Pointcut("@annotation(com.example.demo.common.core.annotation.SysLogNotUser)")
    public void logPointCutNotUser(){

    }

    @Pointcut("@annotation(com.example.demo.common.core.annotation.RedisLock)")
    public void redisLockPointCut(){

    }

    @Pointcut("@annotation(com.example.demo.common.core.annotation.RedisLockDelay)")
    public void redisLockDelayPointCut(){

    }


    @Around("logPointCutNotUser()")
    public Object aroundNotUser(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();

        Object result = point.proceed();

        long time = System.currentTimeMillis() - beginTime;

        saveSysLogNotUser(point, time);
        return result;
    }

    private void saveSysLogNotUser(ProceedingJoinPoint point, long time) {
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();

//        SysLog sysLog = new SysLog();
//        SysLogNotUser sysLogAn = method.getAnnotation(SysLogNotUser.class);
//        if (sysLogAn != null){
//            //注解上的描述
//            sysLog.setOperation(sysLogAn.value());
//        }
//
//        String className = point.getTarget().getClass().getName();
//        String methodName = signature.getName();
//        sysLog.setMethod(className + "." + methodName + "()");
//
//        Object[] args = point.getArgs();
//
//        try {
//            String params = new Gson().toJson(args);
//            sysLog.setParams(params);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
//        sysLog.setIp(IPUtils.getIpAddr(request));
//
//        //String username = ((SysUser) SecurityUtils.getSubject().getPrincipal()).getUsername();
//        //sysLog.setUsername(username);
//
//        sysLog.setTime(time);
//        sysLog.setCreateDate(new Date());
//
//        sysLogService.insert(sysLog);
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();

        Object result = point.proceed();

        long time = System.currentTimeMillis() - beginTime;

        saveSysLog(point, time);
        return result;
    }

    private void saveSysLog(ProceedingJoinPoint point, long time) {
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();

//        SysLog sysLog = new SysLog();
//        SysLogAn sysLogAn = method.getAnnotation(SysLogAn.class);
//        if (sysLogAn != null){
//            //注解上的描述
//            sysLog.setOperation(sysLogAn.value());
//        }
//
//        String className = point.getTarget().getClass().getName();
//        String methodName = signature.getName();
//        sysLog.setMethod(className + "." + methodName + "()");
//
//        Object[] args = point.getArgs();
//
//        try {
//            String params = new Gson().toJson(args);
//            sysLog.setParams(params);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
//        sysLog.setIp(IPUtils.getIpAddr(request));
//
//        String username = ((SysUser) SecurityUtils.getSubject().getPrincipal()).getUsername();
//        sysLog.setUsername(username);
//
//        sysLog.setTime(time);
//        sysLog.setCreateDate(new Date());
//
//        sysLogService.insert(sysLog);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
