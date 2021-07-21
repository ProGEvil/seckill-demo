package com.hdc.seckill.vo;

import com.hdc.seckill.validator.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @ClassName Loginvo
 * @Description 实现自定义注解的方法 手机号密码
 * @Author prog_evil
 * @Date 2021/7/20 10:34 下午
 * @Version 1.0
 **/
@Data
public class LoginVo {
    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;
}
