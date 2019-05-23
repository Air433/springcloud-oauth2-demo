package com.example.demo.user.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.user.api.entity.SysRoleMenu;

import java.util.List;

/**
 * @Author oyg
 * @Date 2018/7/29/21:02
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {


    List<Long> queryMenuIdList(Long roleId);

    void saveOrUpdate(Long roleId, List<Long> menuIdList);

    int deleteBatch(Long[] roleIds);
}
