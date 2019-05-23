package com.example.demo.user.biz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.user.api.entity.SysConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统配置信息表 Mapper 接口
 * </p>
 *
 * @author oyg
 * @since 2018-07-15
 */
@Mapper
public interface SysConfigMapper extends BaseMapper<SysConfig> {

}
