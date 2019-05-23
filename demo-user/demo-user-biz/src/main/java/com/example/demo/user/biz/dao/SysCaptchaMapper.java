package com.example.demo.user.biz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.user.api.entity.SysCaptcha;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统验证码 Mapper 接口
 * </p>
 *
 * @author oyg
 * @since 2018-07-15
 */
@Mapper
public interface SysCaptchaMapper extends BaseMapper<SysCaptcha> {

}
