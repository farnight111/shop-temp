package org.example.shoptemp.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 购物车商品
 * @author czx
 * @date 2021/8/7
 */
@Data
public class ShopCarVO {
    /**
     *
     */
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
     * 商品名
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     *
     */
    private String images;

    /**
     * 商家id
     */
    private Integer ownerId;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 商品数量
     */
    private Integer goodsCount;
    /**
     * 多个商品总价
     */
    private BigDecimal totalPrice;
}
