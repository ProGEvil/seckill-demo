package com.hdc.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hdc.seckill.pojo.Order;
import com.hdc.seckill.pojo.User;
import com.hdc.seckill.vo.GoodsVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hdc
 * @since 2021-07-27
 */
public interface IOrderService extends IService<Order> {

    /*
     * @Author ProG_Evil
     * @Description //秒杀
     * @Date 3:53 下午 2021/8/2
     * @Param [user, goods]
     * @return com.hdc.seckill.pojo.Order
     **/
    Order seckill(User user, GoodsVo goods);
}
