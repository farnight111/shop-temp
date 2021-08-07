package org.example.shoptemp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 订单商品
 * @TableName order_goods
 */
@TableName(value ="order_goods")
@Data
public class OrderGoods implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer orderId;

    /**
     * 
     */
    private Integer goodsId;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private String images;

    /**
     * 
     */
    private Integer ownerId;

    /**
     * 
     */
    private BigDecimal price;

    /**
     * 
     */
    private Integer goodsCount;

    /**
     * 
     */
    private BigDecimal totalPrice;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}