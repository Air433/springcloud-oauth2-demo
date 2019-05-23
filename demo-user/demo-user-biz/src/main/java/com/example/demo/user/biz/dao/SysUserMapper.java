package com.example.demo.user.biz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.user.api.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author oyg
 * @since 2018-07-15
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<String> queryAllPerms(Long userId);

    SysUser queryByUserName(String username);

    /**
     * 查询用户的所有菜单ID
     *
     * @param userId
     * @return
     */
    List<Long> queryAllMenuId(Long userId);
}
