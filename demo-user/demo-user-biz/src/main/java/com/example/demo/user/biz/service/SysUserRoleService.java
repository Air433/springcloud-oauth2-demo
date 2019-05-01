package com.example.demo.user.biz.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.user.api.entity.SysUserRole;

import java.util.List;

/**
 * @Author oyg
 * @Date 2018/8/5/21:25
 */
public interface SysUserRoleService extends IService<SysUserRole> {
    List<Long> queryRoleIdList(Long userId);

    int deleteBatch(Long[] roleIds);

    void saveOrUpdate(Long userId, List<Long> roleIdList);
}
