package com.example.demo.resource;

import com.example.demo.common.security.annotation.AirEnableResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

//@ComponentScan("com.example.demo.common.security.component.PermissionService")
@AirEnableResourceServer
@SpringCloudApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DemoResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoResourceApplication.class, args);
    }
}
