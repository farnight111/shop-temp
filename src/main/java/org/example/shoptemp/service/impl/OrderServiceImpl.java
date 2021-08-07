package org.example.shoptemp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.shoptemp.entity.Goods;
import org.example.shoptemp.entity.Order;
import org.example.shoptemp.entity.OrderGoods;
import org.example.shoptemp.mapper.GoodsMapper;
import org.example.shoptemp.service.OrderGoodsService;
import org.example.shoptemp.service.OrderService;
import org.example.shoptemp.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService{

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private OrderGoodsService orderGoodsService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Order order) {
        List<OrderGoods> orderGoodsList = order.getOrderGoodsList();
        orderGoodsFillProp(orderGoodsList); //给订单商品填充属性
        //计算订单总价格
        BigDecimal orderPrice = calculateOrderPrice(orderGoodsList);
        order.setOrderPrice(orderPrice);
        save(order);
        orderGoodsList.forEach(item -> item.setOrderId(order.getId()));
        orderGoodsService.saveBatch(orderGoodsList);
    }

    @Override
    public Page<Order> listByPage(Page<Order> page, Integer buyerId) {
        Wrapper<Order> queryWrapper = Wrappers.<Order>lambdaQuery()
                .eq(Order::getBuyerId, buyerId);
        Page<Order> resultPage = page(page, queryWrapper);
        for (Order record : resultPage.getRecords()) {
            List<OrderGoods> orderGoods = orderGoodsService.getByOrderId(record.getId());
            record.setOrderGoodsList(orderGoods);
        }
        return resultPage;
    }

    /**
     * 计算订单总价
     * @param orderGoodsList
     * @return
     */
    private BigDecimal calculateOrderPrice(List<OrderGoods> orderGoodsList) {
        return orderGoodsList.stream()
                .map(OrderGoods::getTotalPrice)
                .reduce(NumberUtil::add)
                .orElse(new BigDecimal("0"));
    }

    /**
     * 给订单商品填充属性
     * @param orderGoodsList
     */
    private void orderGoodsFillProp(List<OrderGoods> orderGoodsList) {
        List<Integer> goodsId = transform2GoodsId(orderGoodsList);
        Map<Integer, Goods> goodsIdMap = queryGoodsIdMap(goodsId);
        for (OrderGoods orderGoods : orderGoodsList) {
            Goods goods = goodsIdMap.get(orderGoods.getGoodsId());
            BeanUtil.copyProperties(goods, orderGoods);
            orderGoods.setOrderId(goods.getId());
            orderGoods.setId(null);
            BigDecimal totalPrice = NumberUtil.mul(orderGoods.getGoodsCount(), orderGoods.getPrice());
            orderGoods.setTotalPrice(totalPrice);
        }
    }

    /**
     * 转换出id
     * @param orderGoodsList
     * @return
     */
    private List<Integer> transform2GoodsId(List<OrderGoods> orderGoodsList) {
        return orderGoodsList.stream()
                .map(OrderGoods::getGoodsId)
                .collect(Collectors.toList());
    }

    /**
     * key为商品id，value为商品
     * @param goodsId
     * @return
     */
    private Map<Integer, Goods> queryGoodsIdMap(List<Integer> goodsId) {
        List<Goods> goodsList = goodsMapper.selectBatchIds(goodsId);
        return goodsList.stream()
                .collect(Collectors.toMap(Goods::getId, g -> g));
    }


}




