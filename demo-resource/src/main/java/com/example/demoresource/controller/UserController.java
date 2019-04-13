package com.example.demoresource.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author oyg
 * @Date 2018/11/12/22:27
 */
@RestController
public class UserController {

    @GetMapping(value = "/t")
    public String t(){
        return "苏杉杉";
    }
}
