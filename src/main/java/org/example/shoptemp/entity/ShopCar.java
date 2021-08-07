package org.example.shoptemp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 购物车
 * @TableName shop_car
 */
@TableName(value ="shop_car")
@Data
public class ShopCar implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 买家id
     */
    private Integer buyerId;

    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 商品数量
     */
    private Integer goodsCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 商品数量递增
     * @param count
     */
    public void incrGoodsCount(Integer count) {
        this.goodsCount += count;
    }
}