package com.hdc.seckill.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author hdc
 * @since 2021-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID,手机号码
     */
    @TableId("id")
    private Long id;

    @TableField("nickname")
    private String nickname;

    /**
     * MD5(MD5(pass明文+固定salt)+salt)
     */
    @TableField("password")
    private String password;

    @TableField("slat")
    private String slat;

    /**
     * 头像
     */
    @TableField("head")
    private String head;

    /**
     * 注册时间
     */
    @TableField("register_date")
    private Date registerDate;

    /**
     * 最后一次登陆时间
     */
    @TableField("last_login_date")
    private Date lastLoginDate;

    /**
     * 登录次数
     */
    @TableField("login_count")
    private Integer loginCount;


}
