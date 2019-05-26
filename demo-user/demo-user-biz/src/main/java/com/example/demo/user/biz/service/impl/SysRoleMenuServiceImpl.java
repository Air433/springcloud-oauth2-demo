package com.example.demo.user.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.user.biz.dao.SysRoleMenuMapper;
import com.example.demo.user.api.entity.SysRoleMenu;
import com.example.demo.user.biz.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author oyg
 * @Date 2018/7/29/21:03
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {
    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        return baseMapper.queryMenuIdList(roleId);
    }

    @Override
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        //先删除角色与菜单关系
        deleteBatch(new Long[]{roleId});

        if (menuIdList.size() == 0){
            return;
        }

        List<SysRoleMenu> roleMenuList = new ArrayList<>(menuIdList.size());
        for (Long menuId : menuIdList) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(roleId);
            roleMenuList.add(sysRoleMenu);
        }
        this.saveBatch(roleMenuList);
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        return baseMapper.deleteBatch(roleIds);
    }
}
