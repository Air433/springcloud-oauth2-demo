package com.example.demo.user.api.request;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * @Author ouyanggang
 * @Date 2019/6/30 - 20:44
 */
public class RoleUpdateDTO {

    private Long roleId;
    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String roleName;
    /**
     * 备注
     */
    private String remark;

    private List<Long> menuIdList;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

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
