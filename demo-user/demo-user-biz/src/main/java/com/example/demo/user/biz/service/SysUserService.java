package com.example.demo.user.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.common.core.utils.PageUtils;
import com.example.demo.user.api.dto.UserInfo;
import com.example.demo.user.api.entity.SysUser;
import com.example.demo.user.api.request.UserAddDTO;
import com.example.demo.user.api.request.UserQO;
import com.example.demo.user.api.request.UserUpdateDTO;
import com.example.demo.user.biz.form.RegiserUserReq;

import java.util.List;
import java.util.Map;

/**
 * @
 */
public interface SysUserService extends IService<SysUser> {

    SysUser queryByUserName(String username);

    /**
     * 查询用户的所有菜单ID
     *
     * @param userId
     * @return
     */
    List<Long> queryAllMenuId(Long userId);

    PageUtils queryPage(UserQO userQO, Long createUserId);

    boolean updatePassword(Long userId, String password, String newPassword);

    void update(SysUser user);

    void add(SysUser sysUser);

    void add(UserAddDTO userAddDTO, Long createUserId);

    void save(RegiserUserReq userReq);

    void testRedis() throws Exception;

    UserInfo getUserInfoBy(String username);

    void updateUser(UserUpdateDTO userUpdateDTO, Long updateUserId);

    SysUser getUserInfoById(Long userId);
}
