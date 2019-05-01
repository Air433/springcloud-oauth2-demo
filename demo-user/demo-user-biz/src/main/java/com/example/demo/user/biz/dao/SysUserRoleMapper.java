package com.example.demo.user.biz.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.user.api.entity.SysUserRole;

import java.util.List;

/**
 * <p>
 * 用户与角色对应关系 Mapper 接口
 * </p>
 *
 * @author oyg
 * @since 2018-07-15
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 根据用户ID，获取角色ID列表
     *
     * @param userId
     * @return
     */
    List<Long> queryRoleIdList(Long userId);

    int deleteBatch(Long[] roleIds);
}
