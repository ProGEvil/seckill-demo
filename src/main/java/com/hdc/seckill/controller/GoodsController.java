package com.hdc.seckill.controller;

import com.hdc.seckill.pojo.User;
import com.hdc.seckill.service.IGoodsService;
import com.hdc.seckill.service.IUserService;
import com.hdc.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

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
    @RequestMapping("/toDetail{goodsId}")
    public String toDetail(Model model, User user, @PathVariable Long goodsId){
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        //秒杀开始时间
        Date startDate = goodsVo.getStartDate();
        //秒杀结束时间
        Date endDate = goodsVo.getEndDate();
        //现在时间
        Date nowDate = new Date();
        //标记为秒杀状态
        int secKillStatus = 0;
        //秒杀倒计时
        int remainSeconds = 0;
        if(nowDate.before(startDate)){//秒杀未开始
            remainSeconds = (int) ((startDate.getTime() - nowDate.getTime())/1000);
        }else if(nowDate.after(endDate)){//秒杀已结束
            secKillStatus = 2;
            remainSeconds = -1;
        }else{//秒杀进行中
            secKillStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("user",user);
        model.addAttribute("secKillStatus",secKillStatus);
        model.addAttribute("remainSeconds",remainSeconds);
        model.addAttribute("goods",goodsVo);
        return "goodsDetail";
    }
}
