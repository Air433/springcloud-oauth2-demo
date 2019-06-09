package com.example.demo.user.api.request;


import com.baomidou.mybatisplus.annotation.TableField;
import com.example.demo.common.core.validator.group.AddGroup;
import com.example.demo.common.core.validator.group.UpdateGroup;
import com.example.demo.user.api.entity.SysUser;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * @Author ouyanggang
 * @Date 2019/6/8 - 09:12
 */
public class UserUpdateDTO {

    @NotBlank(message = "用户ID不能为空", groups = {UpdateGroup.class})
    private Long userId;

    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    private Long updateUserId;

    private Date updateTime;

    /**
     * 角色ID列表
     */
    private List<Long> roleIdList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }
}
