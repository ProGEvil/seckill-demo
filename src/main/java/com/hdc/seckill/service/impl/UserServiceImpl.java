package com.hdc.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hdc.seckill.exception.GlobalException;
import com.hdc.seckill.mapper.UserMapper;
import com.hdc.seckill.pojo.User;
import com.hdc.seckill.service.IUserService;
import com.hdc.seckill.utils.MD5Util;
import com.hdc.seckill.utils.ValidatorUtil;
import com.hdc.seckill.vo.LoginVo;
import com.hdc.seckill.vo.RespBean;
import com.hdc.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hdc
 * @since 2021-07-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    /*
     * @Author ProG_Evil
     * @Description //实现登陆功能
     * @Date 11:02 下午 2021/7/20
     * @Param
     * @return
     **/
    @Override
    public RespBean doLogin(LoginVo loginVo){
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
//        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
//        }
//
        //根据手机号获取用户
        User user = userMapper.selectById(mobile);
        if(null == user){
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        //判断密码是否正确
        if(!MD5Util.formPassToDBPass(password,user.getSlat()).equals(user.getPassword())){
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        return RespBean.success();
    }
}
