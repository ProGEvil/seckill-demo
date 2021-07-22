package com.hdc.seckill.exception;

import com.hdc.seckill.vo.RespBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @ClassName GlobalException
 * @Description 全局异常
 * @Author prog_evil
 * @Date 2021/7/22 4:31 下午
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalException extends RuntimeException{
    private RespBeanEnum respBeanEnum;

}
