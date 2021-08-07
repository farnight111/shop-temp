package org.example.shoptemp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.shoptemp.entity.Order;
import org.example.shoptemp.entity.OrderGoods;
import org.example.shoptemp.entity.Result;
import org.example.shoptemp.entity.User;
import org.example.shoptemp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 订单
 * @author czx
 * @date 2021/8/7
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private HttpServletRequest request;

    /**
     * 前端传入json格式如下
     * [
     *   {
     *      "goodsId": 商品id,
     *      "goodsCount": 购买数量
     *   },
     *  ...
     * ]
     * 下单
     * @param orderGoodsList 商品集合
     * @return
     */
    @PostMapping
    public Result create(@RequestBody List<OrderGoods> orderGoodsList) {
        Order order = new Order();
        User buyer = (User) request.getSession().getAttribute("user");
        order.setBuyerId(buyer.getId());
        order.setCreateAt(new Date());
        order.setOrderGoodsList(orderGoodsList);
        orderService.create(order);
        return Result.success();
    }

    @GetMapping("/page")
    public Result<Page<Order>> listByPage(Page<Order> page) {
        User user = (User) request.getSession().getAttribute("user");
        Page<Order> resultPage =orderService.listByPage(page, user.getId());
        return Result.success(resultPage);
    }
}
