package com.hdc.seckill.controller;

import com.hdc.seckill.pojo.User;
import com.hdc.seckill.service.IGoodsService;
import com.hdc.seckill.service.IUserService;
import com.hdc.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName GoodsController
 * @Description controller of goods
 * @Author prog_evil
 * @Date 2021/7/24 10:04 下午
 * @Version 1.0
 **/
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IGoodsService goodsService;
    @RequestMapping("/toList")
    public String toList( Model model, User user){
//        if(StringUtils.isEmpty(ticket)){
//            return "login";
//        }
////        User user = (User) session.getAttribute(ticket);
//        User user = userService.getUserByCookie(ticket, request, response);
//        if(null == user){
//            return "login";
//        }
        model.addAttribute("user",user);
        model.addAttribute("goodsList",goodsService.findGoodsVo());
        return "goodsList";
    }
}
