package com.example.demo.user.biz.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.common.core.response.AirResult;
import com.example.demo.user.biz.dao.SysUserTokenMapper;
import com.example.demo.user.api.entity.SysUserToken;
import com.example.demo.user.biz.service.SysUserTokenService;
import com.example.demo.user.biz.dao.SysUserTokenMapper;
import com.example.demo.user.api.entity.SysUserToken;
import com.example.demo.user.biz.oauth2.TokenGenerator;
import com.example.demo.user.biz.service.SysUserTokenService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author oyg
 * @Date 2018/7/21/16:47
 */
@Service
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenMapper, SysUserToken> implements SysUserTokenService {

    //12小时过期
    private final static int EXPIRE = 3600 * 12;

    @Override
    public AirResult createToekn(long userId) {

        String token = TokenGenerator.generateValue();

        Date now = new Date();

        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        SysUserToken userToken = this.selectById(userId);

        if (userToken == null) {
            userToken = new SysUserToken();
            userToken.setUserId(userId);
            userToken.setToken(token);
            userToken.setUpdateTime(now);
            userToken.setExpireTime(expireTime);

            this.insert(userToken);
        } else {
            userToken.setToken(token);
            userToken.setUpdateTime(now);
            userToken.setExpireTime(expireTime);

            this.updateById(userToken);
        }

        Map<String, Object> res = new HashMap<>();
        res.put("token", token);
        res.put("expire", EXPIRE);

        return AirResult.success("登陆成功", res);
    }

    @Override
    public void logout(Long userId) {
        String token = TokenGenerator.generateValue();

        SysUserToken userToken = new SysUserToken();
        userToken.setUserId(userId);
        userToken.setToken(token);
        this.updateById(userToken);
    }
}
