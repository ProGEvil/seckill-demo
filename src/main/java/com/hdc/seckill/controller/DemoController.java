package com.hdc.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName DemoController
 * @Description 测试
 * @Author prog_evil
 * @Date 2021/7/16 11:28 下午
 * @Version 1.0
 **/
@Controller
@RequestMapping("/demo")
public class DemoController {
    /*
     * @Author ProG_Evil
     * @Description //测试页面跳转
     * @Date 11:42 下午 2021/7/16
     * @Param [model]
     * @return java.lang.String
     **/
    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("name","hdc");
        return "hello";
    }
}
