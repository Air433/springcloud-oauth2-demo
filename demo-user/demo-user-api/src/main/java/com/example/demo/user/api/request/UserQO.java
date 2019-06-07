package com.example.demo.user.api.request;

import com.example.demo.common.core.utils.BasePageModel;

/**
 * 用户列表查询
 * @Author ouyanggang
 * @Date 2019/6/7 - 09:53
 */
public class UserQO extends BasePageModel {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
