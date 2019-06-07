package com.example.demo.user.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.common.core.utils.PageUtils;
import com.example.demo.user.api.entity.SysRole;
import com.example.demo.user.api.request.RoleQO;

/**
 * @Author oyg
 * @Date 2018/8/18/18:18
 */
public interface SysRoleService extends IService<SysRole> {
    PageUtils queryPage(RoleQO roleQO, Long createUserId);

    void add(SysRole role);

    void deleteBatch(Long[] roleIds);

    void updateRole(SysRole sysRole);
}
