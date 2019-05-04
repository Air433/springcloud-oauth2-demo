package com.example.demo.user.api.feign.impl;

import com.example.demo.user.api.dto.UserInfo;
import com.example.demo.user.api.feign.UserClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @Author oyg
 * @Date 2019/4/28/21:07
 */
@Component
public class UserServiceFallbackImpl implements UserClient {

    private static Logger log = LoggerFactory.getLogger(UserServiceFallbackImpl.class);

    private Throwable cause;

    @Override
    public ResponseEntity<UserInfo> getUserInfo(String username) {
        log.error("feign 查询用户信息失败:{}", username, cause);
        return null;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }
}
