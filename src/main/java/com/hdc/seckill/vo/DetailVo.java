package com.hdc.seckill.vo;

import com.hdc.seckill.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName DetailVo
 * @Description 商品详情返回的对象
 * @Author prog_evil
 * @Date 2021/8/6 10:35 上午
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailVo {

    private User user;

    private GoodsVo goodsVo;

    private int secKillStatus;

    private int remainSeconds;
}
