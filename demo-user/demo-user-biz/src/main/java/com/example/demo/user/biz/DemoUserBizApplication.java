package com.example.demo.user.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DemoUserBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoUserBizApplication.class, args);
    }

}
