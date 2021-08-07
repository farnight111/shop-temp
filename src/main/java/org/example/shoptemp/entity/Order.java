package org.example.shoptemp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 订单
 * @TableName order
 */
@TableName(value ="`order`")
@Data
public class Order implements Serializable {
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
     * 订单总价
     */
    private BigDecimal orderPrice;

    /**
     * 下单时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createAt;
    /**
     * 订单商品
     */
    @TableField(exist = false)
    private List<OrderGoods> orderGoodsList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}