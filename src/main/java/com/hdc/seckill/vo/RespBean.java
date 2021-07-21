package com.hdc.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName RespBean
 * @Description TODO
 * @Author prog_evil
 * @Date 2021/7/19 9:59 下午
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    private long code;
    private String message;
    private Object obj;
//成功返回结果
    public static RespBean success(){
        return new RespBean(RespBeanSuccessEnum.SUCCESS.getCode(), RespBeanSuccessEnum.SUCCESS.getMessage(), null);
    }
    public static RespBean success(Object obj){
        return new RespBean(RespBeanSuccessEnum.SUCCESS.getCode(), RespBeanSuccessEnum.SUCCESS.getMessage(), obj);
    }

    //失败返回结果
    public static RespBean error(RespBeanErrorEnum respBeanErrorEnum){
        return new RespBean(respBeanErrorEnum.getCode(), respBeanErrorEnum.getMessage(), null);
    }
    public static RespBean error(RespBeanErrorEnum respBeanErrorEnum,Object obj){
        return new RespBean(respBeanErrorEnum.getCode(), respBeanErrorEnum.getMessage(), obj);
    }
}
