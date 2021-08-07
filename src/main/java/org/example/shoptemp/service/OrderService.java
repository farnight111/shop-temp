package org.example.shoptemp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.shoptemp.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface OrderService extends IService<Order> {
    /**
     * 创建订单
     * @param order
     */
    void create(Order order);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<Order> listByPage(Page<Order> page, Integer buyerId);
}
