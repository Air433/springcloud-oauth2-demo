package com.example.demo.user.api.dto;

import com.example.demo.user.api.entity.SysUser;

import java.io.Serializable;

/**
 * @Author oyg
 * @Date 2019/4/27/22:08
 */
public class UserInfo implements Serializable {

    private SysUser sysUser;

    private String[] roles;

    private String[] permissions;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }
}
