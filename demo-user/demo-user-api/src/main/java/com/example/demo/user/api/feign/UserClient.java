package com.example.demo.user.api.feign;

import com.example.demo.common.core.response.AirResult;
import com.example.demo.user.api.dto.UserInfo;
import com.example.demo.user.api.feign.factory.UserServiceFallbackFactory;
import com.example.demo.user.api.feign.impl.UserServiceFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author oyg
 * @Date 2019/4/27/20:26
 */
@FeignClient(value = "demo-user", fallback = UserServiceFallbackImpl.class)
public interface UserClient {

    @GetMapping("/sys/user/query/{username}")
    ResponseEntity<UserInfo> getUserInfo(@PathVariable("username") String username);
}
