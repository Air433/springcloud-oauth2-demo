package com.example.demo.user.biz.controller;

import com.example.demo.common.core.response.AirResult;
import com.example.demo.common.security.service.SecurityUser;
import com.example.demo.user.api.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

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

        return AirResult.error(ex.getMessage());
    }
}
