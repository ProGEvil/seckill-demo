package com.hdc.seckill.controller;

import com.hdc.seckill.vo.LoginVo;
import com.hdc.seckill.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LoginController
 * @Description 实现登陆跳转功能
 * @Author prog_evil
 * @Date 2021/7/19 9:40 下午
 * @Version 1.0
 **/
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(LoginVo loginVo){
        log.info("{}",loginVo);
        return null;
    }
}
