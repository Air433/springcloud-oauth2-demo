package com.example.demoauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author oyg
 * @Date 2019/3/30/11:01
 */
@RestController
public class WarehouseController {

    @RequestMapping("/print")
    public String print(){
        return "sussssss";
    }
}
