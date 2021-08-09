package com.hdc.seckill.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hdc.seckill.pojo.Order;
import com.hdc.seckill.pojo.SeckillOrder;
import com.hdc.seckill.pojo.User;
import com.hdc.seckill.service.IGoodsService;
import com.hdc.seckill.service.IOrderService;
import com.hdc.seckill.service.ISeckillOrderService;
import com.hdc.seckill.vo.GoodsVo;
import com.hdc.seckill.vo.RespBean;
import com.hdc.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName SecKillController
 * @Description TODO
 * @Author prog_evil
 * @Date 2021/8/2 3:24 下午
 * @Version 1.0
 **/
@Controller
@RequestMapping("/seckill")
public class SecKillController {

    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private ISeckillOrderService seckillOrderService;
    @Autowired
    private IOrderService orderService;
    /*
     * @Author ProG_Evil
     * @Description //秒杀
     * @Date 3:27 下午 2021/8/2
     * @Param [model, user, goodsId]
     * @return java.lang.String
     **/
    @RequestMapping("/doSeckill2")
    public String doSeckill2(Model model, User user, Long goodsId){
        // 先判断用户是否存在
        if(user == null){
            return "login";
        }
        //存在则将用户存放进去
        model.addAttribute("user",user);
        GoodsVo goods = goodsService.findGoodsVoByGoodsId(goodsId);
        //判断库存
        if(goods.getStockCount()<1){
            model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK_ERROR.getMessage());
            return "secKillFail";
        }
        //判断是否重复抢购
        SeckillOrder seckillOrder = seckillOrderService.getOne(new QueryWrapper<SeckillOrder>().eq("user_id", user.getId()
        ).eq("goods_id", goodsId));
        if(seckillOrder!=null){
            model.addAttribute("errmsg",RespBeanEnum.REPEATE_ERROR.getMessage());
            return "secKillFail";
        }
        Order order = orderService.seckill(user,goods);
        model.addAttribute("order",order);
        model.addAttribute("goods",goods);
        return "orderDetail";
    }

    /*
     * @Author ProG_Evil
     * @Description //秒杀
     * @Date 3:27 下午 2021/8/2
     * @Param [model, user, goodsId]
     * @return java.lang.String
     **/
    @RequestMapping(value = "/doSeckill",method = RequestMethod.POST)
    @ResponseBody
    public RespBean doSeckill( Model model,User user, Long goodsId){
        // 先判断用户是否存在
        if(user == null){
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        //存在则将用户存放进去
        GoodsVo goods = goodsService.findGoodsVoByGoodsId(goodsId);
        //判断库存
        if(goods.getStockCount()<1){
            model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK_ERROR.getMessage());
            return RespBean.error(RespBeanEnum.EMPTY_STOCK_ERROR);
        }
        //判断是否重复抢购
        SeckillOrder seckillOrder =
                seckillOrderService.getOne(new QueryWrapper<SeckillOrder>().eq("user_id", user.getId()
        ).eq("goods_id", goodsId));
        if(seckillOrder!=null){
            model.addAttribute("errmsg",RespBeanEnum.REPEATE_ERROR.getMessage());
            return RespBean.error(RespBeanEnum.REPEATE_ERROR);
        }
        Order order = orderService.seckill(user,goods);
        return RespBean.success(order);
    }
}
