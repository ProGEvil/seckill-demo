package com.hdc.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hdc.seckill.pojo.User;
import com.hdc.seckill.vo.LoginVo;
import com.hdc.seckill.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hdc
 * @since 2021-07-18
 */
public interface IUserService extends IService<User> {
    //登陆逻辑接口
    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    //根据cookie获取用户
    User getUserByCookie(String userTicket,HttpServletRequest request,HttpServletResponse response);

    //更改密码
    RespBean updatePassword(String userTicket,String password,HttpServletRequest request,HttpServletResponse response);
}
