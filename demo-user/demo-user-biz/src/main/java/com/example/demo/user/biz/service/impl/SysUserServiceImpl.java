package com.example.demo.user.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.core.exception.RRException;
import com.example.demo.common.core.utils.Constant;
import com.example.demo.common.core.utils.MapUtils;
import com.example.demo.common.core.utils.PageUtils;
import com.example.demo.common.core.utils.Query;
import com.example.demo.user.api.dto.UserInfo;
import com.example.demo.user.api.entity.SysMenu;
import com.example.demo.user.biz.dao.SysRoleMapper;
import com.example.demo.user.biz.dao.SysUserMapper;
import com.example.demo.user.api.entity.SysUser;
import com.example.demo.user.biz.form.RegiserUserReq;
import com.example.demo.user.biz.service.ShiroService;
import com.example.demo.user.biz.service.SysLogService;
import com.example.demo.user.biz.service.SysUserRoleService;
import com.example.demo.user.biz.service.SysUserService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author oyg
 * @Date 2018/7/21/16:26
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysLogService sysLogService;
    @Resource
    private SysRoleMapper roleMapper;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private ShiroService shiroService;


    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    @Override
    public SysUser queryByUserName(String username) {
        return baseMapper.queryByUserName(username);
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return null;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String username = (String) params.get("username");
        Long createUserId = (Long) params.get("createUserId");

        IPage<SysUser> page = this.page(
                new Query<SysUser>().getPage(params),
                new QueryWrapper<SysUser>()
                        .like(StringUtils.isNotBlank(username), "username", username)
                        .eq(createUserId != null, "create_user_id", createUserId)
        );

        return new PageUtils(page);
    }

    @Override
    public boolean updatePassword(Long userId, String password, String newPassword) {
        SysUser sysUser = new SysUser();
        sysUser.setPassword(newPassword);
        return this.update(sysUser,
                new QueryWrapper<SysUser>().eq("user_id", userId).eq("password", password));
    }

    @Override
    @Transactional
    public void update(SysUser user) {
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(null);
        } else {
            user.setPassword(ENCODER.encode(user.getPassword()));
        }
        this.updateById(user);

        //检查角色是否越权

    }

    @Transactional
    @Override
    public void add(SysUser user) {
        user.setCreateTime(new Date());

        String salt = RandomStringUtils.randomAlphabetic(20);
        user.setPassword(ENCODER.encode(user.getPassword()));
        user.setSalt(salt);

        checkRole(user);


    }

    @Transactional(rollbackFor = Exception.class, timeout = 5000)
    @Override
    public void save(RegiserUserReq userReq) {

        String username = userReq.getUsername();

        List<SysUser> list = (List<SysUser>)this.listByMap(new MapUtils().put("username", username));

        if (list != null && list.size() > 0) {
            throw new RRException("用户名已存在");
        }

        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        String salt = RandomStringUtils.randomAlphabetic(20);
        sysUser.setPassword(ENCODER.encode(userReq.getPassword()));
        sysUser.setSalt(salt);

        List<Long> roleId = new ArrayList<>();
        roleId.add(2l);
        sysUser.setRoleIdList(roleId);
        this.save(sysUser);

        sysUserRoleService.saveOrUpdate(sysUser.getUserId(), sysUser.getRoleIdList());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testRedis() throws Exception {
        //Thread.sleep(10000);

        SysUser sysUser = new SysUser();
        sysUser.setUsername("苏杉杉33333");
        sysUser.setPassword("3433443");
        baseMapper.insert(sysUser);

        if (1 == 1) {
            throw new Exception("异常。。。。。");
        }
        //Thread.sleep(20000);
        System.err.println("-------------------超时------------------");
    }

    @Override
    public UserInfo getUserInfoBy(String username) {
        SysUser sysUser = queryByUserName(username);

        UserInfo userInfo = new UserInfo();

        userInfo.setSysUser(sysUser);

        Set<String> permissions = shiroService.getUserPermissions(sysUser.getUserId());

        userInfo.setPermissions(permissions.toArray(new String[]{}));

        return userInfo;
    }

    private void checkRole(SysUser user) {
        if (user.getRoleIdList() == null || user.getRoleIdList().size() == 0) {
            return;
        }
        //如果不是超级管理员，则需要判断用户的角色是否自己创建
        if (user.getCreateUserId() == Constant.SUPER_ADMIN) {
            return;
        }

    }


}
