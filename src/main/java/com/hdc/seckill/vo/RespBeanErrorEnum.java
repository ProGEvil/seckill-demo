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
    ERROR(500,"服务端异常");
    private final Integer code;
    private final String message;
}
