package com.hdc.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @ClassName RespBeanEnum
 * @Description TODO
 * @Author prog_evil
 * @Date 2021/7/19 9:59 下午
 * @Version 1.0
 **/
@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {
    //通用
    SUCCESS(200,"SUCCESS"),
    ERROR(500,"服务端异常"),
    //登陆模块5002xx
    LOGIN_ERROR(500210,"用户名或密码不正确"),
    MOBILE_ERROR(500211,"手机号码不正确"),
    BIND_ERROR(500212,"参数校验异常"),
    //秒杀模块5005xx
    EMPTY_STOCK_ERROR(500500,"库存不足"),
    REPEATE_ERROR(500501,"该商品每人限购一件"),
    ;
    private final Integer code;
    private final String message;
}
