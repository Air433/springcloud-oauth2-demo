package com.example.demo.feign.client;

import org.springframework.stereotype.Component;

/**
 * @Author oyg
 * @Date 2019/4/29/21:26
 */
@Component
public class DemoHiHystric implements DemoHiClient{
    @Override
    public String getUserInfo() {
        return "12113233";
    }
}
