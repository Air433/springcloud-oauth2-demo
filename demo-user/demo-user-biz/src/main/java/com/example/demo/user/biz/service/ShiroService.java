package com.example.demo.user.biz.service;

import com.example.demo.user.api.entity.SysUser;

import java.util.Set;

public interface ShiroService {

    Set<String> getUserPermissions(long userId);

    SysUser queryUser(Long userId);
}
