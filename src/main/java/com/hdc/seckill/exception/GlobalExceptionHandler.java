package com.hdc.seckill.exception;

import com.hdc.seckill.vo.RespBean;
import com.hdc.seckill.vo.RespBeanEnum;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常处理类
 * @Author prog_evil
 * @Date 2021/7/22 4:45 下午
 * @Version 1.0
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public RespBean ExceptionHandler(Exception e){
        //进入判断是否为全局异常
        if(e instanceof GlobalException){
            GlobalException ex = (GlobalException) e;
            return RespBean.error(ex.getRespBeanEnum());
            //不属于全局异常 则进入判断是否为参数校验异常
        }else if(e instanceof BindException){
            BindException ex = (BindException) e;
            //创建一个bean属于校验异常
            RespBean respBean = RespBean.error(RespBeanEnum.BIND_ERROR);
            //set一个提示信息message
            respBean.setMessage("参数校验异常：" + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
            return respBean;
        }
        //如果没有参数校验异常或者全局异常的话 就抛出服务端异常
        return RespBean.error(RespBeanEnum.ERROR);
    }
}
