package com.example.demoauth.model;

import java.io.Serializable;


public class Msg implements Serializable{
    private static final long serialVersionUID = 7514826298158585250L;
    public static final int SUCCESS=200;
    public static final int FAILED=201;
    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
