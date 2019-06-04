package com.example.demo.user.biz.controller;

import com.example.demo.common.core.annotation.SysLogAn;
import com.example.demo.common.core.response.AirResult;
import com.example.demo.common.core.utils.Constant;
import com.example.demo.common.core.utils.PageUtils;
import com.example.demo.common.core.validator.Assert;
import com.example.demo.common.core.validator.ValidatorUtils;
import com.example.demo.common.core.validator.group.AddGroup;
import com.example.demo.common.core.validator.group.UpdateGroup;
import com.example.demo.user.api.dto.UserInfo;
import com.example.demo.user.api.entity.SysUser;
import com.example.demo.user.biz.form.PasswordForm;
import com.example.demo.user.biz.service.SysUserRoleService;
import com.example.demo.user.biz.service.SysUserService;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author oyg
 * @Date 2018/7/29/21:17
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    @GetMapping("/list")
    @PreAuthorize("@ps.hasPermission('sys:user:list')")
    public AirResult list(@RequestParam Map<String, Object> params) {
        //只有超级管理员可以查询所有管理员列表
        if (getUserId() != Constant.SUPER_ADMIN) {
            params.put("createdUserId", getUser());
        }
        PageUtils page = sysUserService.queryPage(params);
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        return AirResult.success(map);
    }

    @GetMapping("/info")
    public AirResult info() {
        Map<String, Object> map = new HashMap<>();
        map.put("user", getUser());
        return AirResult.success(map);
    }

    @SysLogAn("修改密码")
    @PutMapping("/password")
    public AirResult password(@RequestBody PasswordForm form) {

        Assert.isBlank(form.getNewPassword(), "新密码不能为空");

        String password = ENCODER.encode(form.getPassword());

        String newPassword =
                ENCODER.encode(form.getPassword());

        boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
        if (!flag) {
            return AirResult.error("原密码不正确");
        }
        return AirResult.success();
    }

    @GetMapping("/info/{userId}")
    @PreAuthorize("@ps.hasPermission('sys:user:info')")
    public AirResult info(@PathVariable("userId") Long userId) {
        SysUser user = sysUserService.getById(userId);

        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);

        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        return AirResult.success(map);
    }

    @SysLogAn("保存用户")
    @PostMapping("/save")
    @PreAuthorize("@ps.hasPermission('sys:user:save')")
    public AirResult save(@RequestBody SysUser sysUser) {
        ValidatorUtils.validateEntity(sysUser, AddGroup.class);

        sysUser.setCreateUserId(getUserId());
        sysUserService.add(sysUser);
        return AirResult.success();
    }

    /**
     * 删除用户
     *
     * @param userIds
     * @return
     */
    @SysLogAn("删除用户")
    @DeleteMapping("/delete")
    @PreAuthorize("@ps.hasPermission('sys:user:delete')")
    public AirResult delete(@RequestBody Long[] userIds) {
        if (ArrayUtils.contains(userIds, 1L)) {
            return AirResult.error("系统管理员不能删除");
        }

        if (ArrayUtils.contains(userIds, getUserId())) {
            return AirResult.error("当前用户不能删除");
        }

        sysUserService.removeByIds(Arrays.asList(userIds));

        return AirResult.success();
    }

    @GetMapping(value = "/query/{username}")
    public ResponseEntity<UserInfo> getUserInfo(@PathVariable String username){

        UserInfo userInfo =  sysUserService.getUserInfoBy(username);

        return new ResponseEntity<>(userInfo,HttpStatus.OK);

    }
}
