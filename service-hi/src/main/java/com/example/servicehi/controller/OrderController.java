package com.example.servicehi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author oyg
 * @Date 2019/3/30/08:12
 */
@RestController
public class OrderController {

    @GetMapping(value = "/s")
    public String t(){
        return "苏杉杉";
    }
}
