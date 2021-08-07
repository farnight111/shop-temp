package org.example.shoptemp.service.impl;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.shoptemp.entity.ShopCar;
import org.example.shoptemp.service.ShopCarService;
import org.example.shoptemp.mapper.ShopCarMapper;
import org.example.shoptemp.vo.ShopCarVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 */
@Service
public class ShopCarServiceImpl extends ServiceImpl<ShopCarMapper, ShopCar> implements ShopCarService{
    @Override
    public void add(Integer goodsId, Integer goodsCount, Integer buyerId) {
        //判断用户是否已经加购了该商品
        ShopCar shopCar = getByGoodsIdAndBuyerId(goodsId, buyerId);
        if (shopCar != null) {
            shopCar.incrGoodsCount(goodsCount);
            updateById(shopCar);
        } else {
            shopCar = generate(goodsId, goodsCount, buyerId);
            save(shopCar);
        }
    }

    @Override
    public Page<ShopCarVO> listByPage(Page<ShopCar> page, Integer buyerId) {
        Page<ShopCarVO> resultPage = baseMapper.selectByPage(page, buyerId);
        List<ShopCarVO> records = resultPage.getRecords();
        calculateTotalPrice(records);
        return resultPage;
    }

    /**
     * 计算总价
     * @param shopCarList
     */
    private void calculateTotalPrice(List<ShopCarVO> shopCarList) {
        for (ShopCarVO shopCarVO : shopCarList) {
            BigDecimal price = shopCarVO.getPrice();
            Integer goodsCount = shopCarVO.getGoodsCount();
            BigDecimal totalPrice = NumberUtil.mul(price, goodsCount);
            shopCarVO.setTotalPrice(totalPrice);
        }
    }

    /**
     * 构造购物车
     * @param goodsId
     * @param goodsCount
     * @param buyerId
     * @return
     */
    private ShopCar generate(Integer goodsId, Integer goodsCount, Integer buyerId) {
        final ShopCar shopCar = new ShopCar();
        shopCar.setGoodsId(goodsId);
        shopCar.setBuyerId(buyerId);
        shopCar.setGoodsCount(goodsCount);
        return shopCar;
    }

    private ShopCar getByGoodsIdAndBuyerId(Integer goodsId, Integer buyerId) {
        Wrapper<ShopCar> queryWrapper = Wrappers.<ShopCar>lambdaQuery()
                .eq(ShopCar::getGoodsId, goodsId)
                .eq(ShopCar::getBuyerId, buyerId)
                .last("limit 1");
        return getOne(queryWrapper);
    }
}




