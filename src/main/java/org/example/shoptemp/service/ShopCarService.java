package org.example.shoptemp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.shoptemp.entity.ShopCar;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.shoptemp.vo.ShopCarVO;

/**
 *
 */
public interface ShopCarService extends IService<ShopCar> {
    /**
     * 添加购物车
     * @param goodsId
     * @param goodsCount
     * @param buyerId
     */
    void add(Integer goodsId, Integer goodsCount,Integer buyerId);

    /**
     * 分页查询
     * @param page
     * @param buyerId
     * @return
     */
    Page<ShopCarVO> listByPage(Page<ShopCar> page, Integer buyerId);
}
