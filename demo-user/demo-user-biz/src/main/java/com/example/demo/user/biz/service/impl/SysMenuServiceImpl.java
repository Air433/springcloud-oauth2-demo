package com.example.demo.user.biz.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.common.core.utils.Constant;
import com.example.demo.common.core.utils.MapUtils;
import com.example.demo.user.biz.dao.SysMenuMapper;
import com.example.demo.user.api.entity.SysMenu;
import com.example.demo.user.biz.service.SysMenuService;
import com.example.demo.user.biz.service.SysRoleMenuService;
import com.example.demo.user.biz.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author oyg
 * @Date 2018/7/21/22:02
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysMenu> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenu> menuList = queryListParentId(parentId);
        if (menuIdList == null) {
            return menuList;
        }

        List<SysMenu> userMenuList = new ArrayList<>();
        for (SysMenu sysMenu : menuList) {
            if (menuIdList.contains(sysMenu.getMenuId())) {
                userMenuList.add(sysMenu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SysMenu> queryListParentId(Long parentId) {
        return baseMapper.queryListParentId(parentId);
    }

    @Override
    public List<SysMenu> getUserMenuList(Long userId) {
        if (userId == Constant.SUPER_ADMIN) {
            return getAllMenList(null);
        }

        //用户菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
        return getAllMenList(menuIdList);
    }

    @Override
    public List<SysMenu> queryNotButtonList() {
        return baseMapper.queryNotButtonList();
    }

    @Override
    public boolean delete(Long menuId) {
        //删除菜单
        this.delete(menuId);
        //删除关联表
        return sysRoleMenuService.deleteByMap(new MapUtils().put("menu_id", menuId));
    }

    private List<SysMenu> getAllMenList(List<Long> menuIdList) {
        //查询根菜单列表
        List<SysMenu> menuList = queryListParentId(0L, menuIdList);

        getMenuTreeList(menuList, menuIdList);

        return menuList;

    }

    private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<Long> menuIdList) {
        List<SysMenu> subMenuList = new ArrayList<>();

        for (SysMenu sysMenu : menuList) {

            if (sysMenu.getType() == Constant.MenuType.CATALOG.getValue()) {
                sysMenu.setList(getMenuTreeList(queryListParentId(sysMenu.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(sysMenu);
        }
        return subMenuList;
    }
}
