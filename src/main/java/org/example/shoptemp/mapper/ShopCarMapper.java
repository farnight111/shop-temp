package org.example.shoptemp.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.example.shoptemp.entity.ShopCar;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.shoptemp.vo.ShopCarVO;

/**
 * @Entity org.example.shoptemp.entity.ShopCar
 */
public interface ShopCarMapper extends BaseMapper<ShopCar> {
    /**
     * 分页查询
     * @param page
     * @param buyerId
     * @return
     */
    Page<ShopCarVO> selectByPage(@Param("page") Page<ShopCar> page,@Param("buyerId") Integer buyerId);
}




