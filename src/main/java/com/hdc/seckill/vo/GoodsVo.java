package com.hdc.seckill.vo;

import com.hdc.seckill.pojo.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName GoodsVo
 * @Description 商品返回对象
 * @Author prog_evil
 * @Date 2021/7/27 9:14 下午
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsVo extends Goods {
    private BigDecimal seckillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
