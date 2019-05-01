package com.example.demo.user.api.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统验证码
 * </p>
 *
 * @author oyg
 * @since 2018-07-15
 */
@TableName("sys_captcha")
public class SysCaptcha extends Model<SysCaptcha> {

    private static final long serialVersionUID = 1L;

    /**
     * uuid
     */
    private String uuid;
    /**
     * 验证码
     */
    private String code;
    /**
     * 过期时间
     */
    @TableField("expire_time")
    private Date expireTime;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.uuid;
    }

    @Override
    public String toString() {
        return "SysCaptcha{" +
                "uuid=" + uuid +
                ", code=" + code +
                ", expireTime=" + expireTime +
                "}";
    }
}
