package com.example.demo.user.biz.controller;

import com.example.demo.common.core.annotation.SysLogNotUser;
import com.example.demo.common.core.response.AirResult;
import com.example.demo.common.core.validator.ValidatorUtils;
import com.example.demo.user.biz.form.RegiserUserReq;
import com.example.demo.user.biz.service.SysUserService;
import com.example.demo.user.biz.service.SysUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author oyg
 * @Date 2018/7/21/12:52
 */
@RestController
@Scope(value = "request")
public class SysLoginController extends AbstractController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;

//    @PostMapping("/sys/login")
//    public AirResult login(@RequestBody SysLoginRequest request){
//        if (request == null){
//            return AirResult.error("请求信息为空，请检查");
//        }
//
//        if (request.getUsername() == null || request.getUsername().trim().equals("")){
//            return AirResult.error("用户名不能为空");
//        }
//        SysUser user = sysUserService.queryByUserName(request.getUsername());
//
//        if (user == null || !user.getPassword().equals(new Sha256Hash(request.getPassword(), user.getSalt()).toHex())){
//            return AirResult.error("账号或密码不正确");
//        }
//
//        if (user.getStatus() == 0){
//            return AirResult.error("账号已被锁定，请联系管理员");
//        }
//
//        AirResult result = sysUserTokenService.createToekn(user.getUserId());
//
//        return result;
//    }

    @PostMapping("/sys/logout")
    public AirResult logout() {
        sysUserTokenService.logout(getUserId());
        return AirResult.success("退出成功");
    }


    @SysLogNotUser("注册用户")
    @PostMapping("/sys/regier")
    public AirResult regiser(@RequestBody RegiserUserReq userReq) {
        ValidatorUtils.validateEntity(userReq);

        sysUserService.save(userReq);

        return AirResult.success();
    }

}
