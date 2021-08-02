package com.hdc.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hdc.seckill.pojo.Goods;
import com.hdc.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hdc
 * @since 2021-07-27
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    //获取商品列表
    List<GoodsVo> findGoodsVo();
    //获取商品详情
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
