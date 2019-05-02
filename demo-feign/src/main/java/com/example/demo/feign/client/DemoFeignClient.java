package com.example.demo.feign.client;

import com.example.demo.user.api.dto.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author oyg
 * @Date 2019/4/29/20:58
 */
@FeignClient(value = "demo-user")
public interface DemoFeignClient {

    @GetMapping("/sys/user/{username}")
    ResponseEntity<UserInfo> getUserInfo(@PathVariable("username") String username);
}
