package com.example.demo.user.biz.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.user.api.entity.SysRoleMenu;

import java.util.List;

/**
 * <p>
 * 角色与菜单对应关系 Mapper 接口
 * </p>
 *
 * @author oyg
 * @since 2018-07-15
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    /**
     * 根据角色ID，获取菜单ID列表
     *
     * @param roleId
     * @return
     */
    List<Long> queryMenuIdList(Long roleId);

    int deleteBatch(Long[] roleIds);
}
