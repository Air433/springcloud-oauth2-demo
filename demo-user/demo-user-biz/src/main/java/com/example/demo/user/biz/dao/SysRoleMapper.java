package com.example.demo.user.biz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.user.api.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author oyg
 * @since 2018-07-15
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

}
