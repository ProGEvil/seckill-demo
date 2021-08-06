package com.hdc.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hdc.seckill.exception.GlobalException;
import com.hdc.seckill.mapper.UserMapper;
import com.hdc.seckill.pojo.User;
import com.hdc.seckill.service.IUserService;
import com.hdc.seckill.utils.CookieUtil;
import com.hdc.seckill.utils.MD5Util;
import com.hdc.seckill.utils.UUIDUtil;
import com.hdc.seckill.vo.LoginVo;
import com.hdc.seckill.vo.RespBean;
import com.hdc.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    @Autowired
    private RedisTemplate redisTemplate;
    /*
     * @Author ProG_Evil
     * @Description //实现登陆功能
     * @Date 11:02 下午 2021/7/20
     * @Param
     * @return
     **/
    @Override
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response){
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
        //生成Cookie
        String ticket = UUIDUtil.uuid();
        //将用户信息存入redis中
        redisTemplate.opsForValue().set("user:" + ticket,user);
//        request.getSession().setAttribute(ticket,user);
        CookieUtil.setCookie(request,response,"userTicket",ticket);
        return RespBean.success(ticket);
    }

    //根据cookie获取用户

    @Override
    public User getUserByCookie(String userTicket,HttpServletRequest request,HttpServletResponse response) {
        if(StringUtils.isEmpty(userTicket)){
            return null;
        }
        User user = (User) redisTemplate.opsForValue().get("user:" + userTicket);
        if(user != null){
            CookieUtil.setCookie(request,response,"userTicket",userTicket);
        }
        return user;
    }

    //更新密码
    @Override
    public RespBean updatePassword(String userTicket, String password,HttpServletRequest request,
                                   HttpServletResponse response) {
        User user = getUserByCookie(userTicket,request,response);
        if(user == null){
            throw new GlobalException(RespBeanEnum.UPDATE_PWD_FAIL);
        }
        user.setPassword(MD5Util.inputPassToDBPass(password,user.getSlat()));
        //取出user的id值 存入result结果集中
        int result = userMapper.updateById(user);
        if(result == 1){
            //通过userTicket删除Redis
            redisTemplate.delete("users" + userTicket);
            return RespBean.success();
        }
        return RespBean.error(RespBeanEnum.UPDATE_PWD_FAIL);
    }
}
