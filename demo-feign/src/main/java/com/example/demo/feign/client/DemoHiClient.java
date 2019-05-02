package com.example.demo.feign.client;

import com.example.demo.user.api.dto.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author oyg
 * @Date 2019/4/29/21:20
 */
@FeignClient(value = "service-hi", fallback = DemoHiHystric.class)
public interface DemoHiClient {
    @GetMapping("/s")
    String getUserInfo();
}
