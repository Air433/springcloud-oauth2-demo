package com.example.demo.user.api.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 系统配置信息表
 * </p>
 *
 * @author oyg
 * @since 2018-07-15
 */
@TableName("sys_config")
public class SysConfig extends Model<SysConfig> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * key
     */
    @TableField("param_key")
    private String paramKey;
    /**
     * value
     */
    @TableField("param_value")
    private String paramValue;
    /**
     * 状态   0：隐藏   1：显示
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysConfig{" +
                "id=" + id +
                ", paramKey=" + paramKey +
                ", paramValue=" + paramValue +
                ", status=" + status +
                ", remark=" + remark +
                "}";
    }
}
