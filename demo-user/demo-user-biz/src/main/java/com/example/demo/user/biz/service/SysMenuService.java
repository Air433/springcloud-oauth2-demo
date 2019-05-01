package com.example.demo.user.biz.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.user.api.entity.SysMenu;

import java.util.List;

/**
 * @Author oyg
 * @Date 2018/7/21/22:01
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId   父菜单ID
     * @param menuIdList 用户菜单ID
     * @return
     */
    List<SysMenu> queryListParentId(Long parentId, List<Long> menuIdList);

    List<SysMenu> queryListParentId(Long parentId);

    /**
     * 获取用户菜单列表
     *
     * @param userId
     * @return
     */
    List<SysMenu> getUserMenuList(Long userId);

    List<SysMenu> queryNotButtonList();

    boolean delete(Long menuId);
}
