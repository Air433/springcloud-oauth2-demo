package com.example.demo.user.api.request;

import com.baomidou.mybatisplus.annotation.TableField;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * @Author ouyanggang
 * @Date 2019/6/30 - 11:50
 */
public class RoleAddDTO {

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    @TableField("role_name")
    private String roleName;
    /**
     * 备注
     */
    private String remark;

    private List<Long> menuIdList;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Long> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Long> menuIdList) {
        this.menuIdList = menuIdList;
    }
}
