package com.hdc.seckill.vo;

import com.hdc.seckill.utils.ValidatorUtil;
import com.hdc.seckill.validator.IsMobile;
import org.thymeleaf.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName IsMobileValidator
 * @Description 手机号码的自定义规则流程
 * @Author prog_evil
 * @Date 2021/7/21 10:28 下午
 * @Version 1.0
 **/
public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {

    private boolean required = false;
    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(required){
            return ValidatorUtil.isMobile(value);
        }else {
            if(StringUtils.isEmpty(value)){
                return true;
            }else {
                return ValidatorUtil.isMobile(value);
            }
        }
    }
}
