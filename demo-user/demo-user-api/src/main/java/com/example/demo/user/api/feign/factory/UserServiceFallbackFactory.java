package com.example.demo.user.api.feign.factory;

import com.example.demo.user.api.feign.UserClient;
import com.example.demo.user.api.feign.impl.UserServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Author oyg
 * @Date 2019/4/28/22:27
 */
@Component
public class UserServiceFallbackFactory implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable throwable) {
        UserServiceFallbackImpl userServiceFallback = new UserServiceFallbackImpl();
        userServiceFallback.setCause(throwable);
        return userServiceFallback;
    }
}
