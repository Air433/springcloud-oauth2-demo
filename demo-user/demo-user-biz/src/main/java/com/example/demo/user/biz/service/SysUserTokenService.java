package com.example.demo.user.biz.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.common.core.response.AirResult;
import com.example.demo.user.api.entity.SysUserToken;

/**
 * @Author oyg
 * @Date 2018/7/21/16:46
 */
public interface SysUserTokenService extends IService<SysUserToken> {

    AirResult createToekn(long userId);

    void logout(Long userId);
}
