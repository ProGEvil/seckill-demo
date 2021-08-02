package com.hdc.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hdc.seckill.mapper.OrderMapper;
import com.hdc.seckill.pojo.Order;
import com.hdc.seckill.pojo.SeckillGoods;
import com.hdc.seckill.pojo.SeckillOrder;
import com.hdc.seckill.pojo.User;
import com.hdc.seckill.service.IOrderService;
import com.hdc.seckill.service.ISeckillGoodsService;
import com.hdc.seckill.service.ISeckillOrderService;
import com.hdc.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hdc
 * @since 2021-07-27
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private ISeckillGoodsService seckillGoodsService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ISeckillOrderService orderService;
    /*
     * @Author ProG_Evil
     * @Description //秒杀
     * @Date 3:54 下午 2021/8/2
     * @Param [user, goods]
     * @return com.hdc.seckill.pojo.Order
     **/
    @Override
    public Order seckill(User user, GoodsVo goods){
        //秒杀商品表减库存
        //秒杀的商品表中去获取一个商品 因为如果拿前端的数据会被别人作假
        SeckillGoods seckillGoods = seckillGoodsService.getOne(new QueryWrapper<SeckillGoods>().eq
                ("goods_id", goods.getId()));
        seckillGoods.setStockCount(seckillGoods.getStockCount()-1);
        seckillGoodsService.updateById(seckillGoods);
        //生成订单
        Order order = new Order();
        order.setUserId(user.getId());
        order.setGoodsId(goods.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodsName(goods.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(seckillGoods.getSeckillPrice());
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setCreateDate(new Date());
        orderMapper.insert(order);
        //生成秒杀订单
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setUserId(user.getId());
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setGoodsId(goods.getId());
        orderService.save(seckillOrder);

        return order;
    }
}
