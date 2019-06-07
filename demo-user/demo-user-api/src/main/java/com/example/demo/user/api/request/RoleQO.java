package com.example.demo.user.api.request;

import com.example.demo.common.core.utils.BasePageModel;

/**
 * @Author ouyanggang
 * @Date 2019/6/6 - 22:44
 */
public class RoleQO extends BasePageModel {

    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


}
