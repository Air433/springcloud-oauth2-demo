package com.example.demo.user.biz.controller;

import com.example.demo.common.core.exception.RRException;
import com.example.demo.common.core.response.AirResult;
import com.example.demo.common.security.service.SecurityUser;
import com.example.demo.user.api.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Author oyg
 * @Date 2018/7/21/12:53
 */
public abstract class AbstractController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SecurityUser getUser() {
        SecurityUser securityUser = (SecurityUser) (SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());
        return  securityUser;
    }

    protected Long getUserId(){
        return getUser().getUserId();
    }

    @ExceptionHandler
    public AirResult exp(HttpServletRequest request, Exception ex){

        if (ex instanceof RRException){
            return AirResult.build(((RRException) ex).getCode(), ((RRException) ex).getMsg());
        }

        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw, true));
        logger.error(sw.toString());

        return AirResult.error(ex.getMessage());
    }
}
