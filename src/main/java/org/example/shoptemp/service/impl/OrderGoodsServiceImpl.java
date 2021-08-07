package org.example.shoptemp.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.shoptemp.entity.OrderGoods;
import org.example.shoptemp.service.OrderGoodsService;
import org.example.shoptemp.mapper.OrderGoodsMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class OrderGoodsServiceImpl extends ServiceImpl<OrderGoodsMapper, OrderGoods> implements OrderGoodsService{

    @Override
    public List<OrderGoods> getByOrderId(Integer orderId) {
        Wrapper<OrderGoods> queryWrapper = Wrappers.<OrderGoods>lambdaQuery()
                .eq(OrderGoods::getOrderId, orderId);
        return list(queryWrapper);
    }
}




