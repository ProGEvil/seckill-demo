package com.hdc.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName RespBean
 * @Description 定义登陆成功和失败的弹出信息方法
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
        return new RespBean(RespBeanEnum.SUCCESS.getCode(), RespBeanEnum.SUCCESS.getMessage(), null);
    }
    public static RespBean success(Object obj){
        return new RespBean(RespBeanEnum.SUCCESS.getCode(), RespBeanEnum.SUCCESS.getMessage(), obj);
    }

    //失败返回结果
    public static RespBean error(RespBeanEnum  respBeanEnum){
        return new RespBean(respBeanEnum.ERROR.getCode(), respBeanEnum.ERROR.getMessage(), null);
    }
    public static RespBean error(RespBeanEnum respBeanEnum,Object obj){
        return new RespBean(respBeanEnum.ERROR.getCode(), respBeanEnum.ERROR.getMessage(), obj);
    }
}
