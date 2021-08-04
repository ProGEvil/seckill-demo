package com.hdc.seckill.controller;

import com.hdc.seckill.pojo.User;
import com.hdc.seckill.service.IGoodsService;
import com.hdc.seckill.service.IUserService;
import com.hdc.seckill.vo.GoodsVo;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    @RequestMapping(value = "/toList",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String toList(Model model, User user, HttpServletRequest request, HttpServletResponse response){
        //redis中获取页面,如果不为空，直接返回页面
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String html = (String) valueOperations.get("goodsList");
        if(!StringUtils.isEmpty(html)){
            return html;
        }

        model.addAttribute("user",user);
        model.addAttribute("goodsList",goodsService.findGoodsVo());
        //return "goodsList"
        //如果为空,手动渲染,存入Redis并返回
        WebContext webContext = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goodsList",webContext);
        if(!StringUtils.isEmpty(html)){
            valueOperations.set("goodsList",html,60, TimeUnit.MINUTES);
        }
        return null;
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
