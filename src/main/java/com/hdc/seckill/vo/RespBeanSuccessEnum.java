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
public enum RespBeanSuccessEnum {
    SUCCESS(200,"SUCCESS");
    private final Integer code;
    private final String message;
}
