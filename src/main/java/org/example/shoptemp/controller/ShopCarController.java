package org.example.shoptemp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.shoptemp.entity.Result;
import org.example.shoptemp.entity.ShopCar;
import org.example.shoptemp.entity.User;
import org.example.shoptemp.service.GoodsService;
import org.example.shoptemp.service.ShopCarService;
import org.example.shoptemp.utils.StringUtil;
import org.example.shoptemp.vo.ShopCarVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车
 * @author czx
 * @date 2021/8/7
 */
@RequestMapping("/shop-car")
@RestController
public class ShopCarController {
    @Autowired
    private ShopCarService shopCarService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private HttpServletRequest request;

    /**
     * 商品添加购物车
     * @param goodsId 商品id
     * @param goodsCount 商品数量
     * @return
     */
    @PostMapping
    public Result add(Integer goodsId, Integer goodsCount) {
        User user = (User) request.getSession().getAttribute("user");
        shopCarService.add(goodsId, goodsCount, user.getId());
        return Result.success();
    }

    /**
     * 减少购物车商品
     * @param id 购物车id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        shopCarService.removeById(id);
        return Result.success();
    }

    /**
     * 分页查询
     * @param size 每页数量
     * @param current 当前页数
     * @return
     */
    @GetMapping("/page")
    public Result<Page<ShopCarVO>> listByPage(Page<ShopCar> page) {
        User user = (User) request.getSession().getAttribute("user");
        Page<ShopCarVO> resultPage = shopCarService.listByPage(page, user.getId());
        return Result.success(resultPage);
    }

}
