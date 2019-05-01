package com.example.demo.user.biz.service;

import com.example.demo.user.api.entity.SysUser;
import com.example.demo.user.api.entity.SysUserToken;

import java.util.Set;

public interface ShiroService {

    Set<String> getUserPermissions(long userId);

    SysUserToken queryByToken(String token);

    SysUser queryUser(Long userId);
}
