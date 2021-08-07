package org.example.shoptemp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品表
 * @TableName goods
 */
@TableName(value ="goods")
@Data
public class Goods implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

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
     * 价格
     */
    private BigDecimal price;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
