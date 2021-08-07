package org.example.shoptemp.service;

import org.example.shoptemp.entity.OrderGoods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface OrderGoodsService extends IService<OrderGoods> {
    /**
     * 根据订单id查询订单的商品
     * @param orderId
     * @return
     */
    List<OrderGoods> getByOrderId(Integer orderId);
}
