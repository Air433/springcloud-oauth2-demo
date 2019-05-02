package com.example.demo.feign.client;

import com.example.demo.user.api.dto.UserInfo;
import com.example.demo.user.api.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author oyg
 * @Date 2019/4/29/21:01
 */
@RestController
public class HelloController {
    @Autowired
    private DemoFeignClient demoFeignClient;

    @Autowired
    private DemoHiClient demoHiClient;

    @GetMapping(value = "/hi")
    public void say(){
        ResponseEntity<UserInfo> userInfo = demoFeignClient.getUserInfo("admin");
        System.err.println(userInfo.getBody().getSysUser());
    }

    @GetMapping(value = "/hi2")
    public void say2(){
        String userInfo = demoHiClient.getUserInfo();
        System.err.println(userInfo);
    }
}
