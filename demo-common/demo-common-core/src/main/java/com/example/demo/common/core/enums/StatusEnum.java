package com.example.demo.common.core.enums;

/**
 * @Author ouyanggang
 * @Date 2019/6/30 - 11:13
 */
public enum StatusEnum {

    USERNAME_EXIST(301, "用户名已经存在");

    private int status;

    private String describe;

    StatusEnum(int status,String describe){
        this.status = status;
        this.describe = describe;
    };

    public String getDescribe() {
        return describe;
    }

    public int getStatus() {
        return status;
    }}
