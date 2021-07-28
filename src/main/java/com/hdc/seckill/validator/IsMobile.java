package com.hdc.seckill.validator;

import com.hdc.seckill.vo.IsMobileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/*
 * @Author ProG_Evil
 * @Description //创建自定义注解
 * @Date 10:24 下午 2021/7/21
 * @Param
 * @return
 **/
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {IsMobileValidator.class}
)
public @interface IsMobile {
    //定义一个required方法 用于表示必填 默认为true
    boolean required() default true;
    //定义message方法 用于输出提示信息
    String message() default "手机号码格式错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
