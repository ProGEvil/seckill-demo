package com.hdc.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hdc.seckill.pojo.Goods;
import com.hdc.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hdc
 * @since 2021-07-27
 */
public interface IGoodsService extends IService<Goods> {
//    获取商品列表
    public List<GoodsVo> findGoodsVo();

    //获取商品详情
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
