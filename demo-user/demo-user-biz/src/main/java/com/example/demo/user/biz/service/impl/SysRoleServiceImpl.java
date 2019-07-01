package com.example.demo.user.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.core.exception.RRException;
import com.example.demo.common.core.utils.Constant;
import com.example.demo.common.core.utils.PageUtils;
import com.example.demo.common.core.utils.Query;
import com.example.demo.user.api.request.RoleAddDTO;
import com.example.demo.user.api.request.RoleQO;
import com.example.demo.user.api.request.RoleUpdateDTO;
import com.example.demo.user.biz.dao.SysRoleMapper;
import com.example.demo.user.api.entity.SysRole;
import com.example.demo.user.biz.service.SysRoleMenuService;
import com.example.demo.user.biz.service.SysRoleService;
import com.example.demo.user.biz.service.SysUserRoleService;
import com.example.demo.user.biz.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author oyg
 * @Date 2018/8/18/18:20
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public PageUtils<SysRole> queryPage(RoleQO roleQO, Long createUserId) {

        IPage<SysRole> page = this.page(
                new Query<SysRole>().getPage(roleQO),
                new QueryWrapper<SysRole>()
                        .like(StringUtils.isNotBlank(roleQO.getRoleName()), "role_name", roleQO.getRoleName())
                        .eq(createUserId != null, "create_user_id", createUserId)
        );
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SysRole role) {
        role.setCreateTime(new Date());
        this.save(role);

        //检查权限是否越权
        checkPerms(role);

        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(RoleAddDTO roleAddDTO, Long createUserId) {

        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleAddDTO, sysRole);

        sysRole.setCreateUserId(createUserId);

        add(sysRole);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除角色
        this.removeByIds(Arrays.asList(roleIds));

        //删除角色与菜单关联
        sysRoleMenuService.deleteBatch(roleIds);

        //删除角色与用户关联
        sysUserRoleService.deleteBatch(roleIds);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateRole(SysRole sysRole) {
        this.updateById(sysRole);

        checkPerms(sysRole);

        sysRoleMenuService.saveOrUpdate(sysRole.getRoleId(), sysRole.getMenuIdList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateRole(RoleUpdateDTO roleUpdateDTO) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleUpdateDTO, sysRole);

        updateRole(sysRole);
    }

    private void checkPerms(SysRole role) {
        //如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
        if (role.getCreateUserId() == Constant.SUPER_ADMIN) {
            return;
        }

        //查询用户所拥有的菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(role.getCreateUserId());

        //判断是否越权
        if (!menuIdList.containsAll(role.getMenuIdList())) {
            throw new RRException("新增角色的权限，已超出你的权限范围");
        }
    }
}
