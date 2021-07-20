package com.hdc.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @ClassName RespBeanErrorEnum
 * @Description TODO
 * @Author prog_evil
 * @Date 2021/7/19 10:21 下午
 * @Version 1.0
 **/
@Getter
@ToString
@AllArgsConstructor
public enum RespBeanErrorEnum {
    //通用
    ERROR(500,"服务端异常"),
    LOGIN_ERROR(500210,"用户名或密码不正确"),
    MOBILE_ERROR(500211,"手机号码不正确");

    private final Integer code;
    private final String message;
}
