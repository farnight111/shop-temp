package org.example.shoptemp.controller;

import org.example.shoptemp.entity.Goods;
import org.example.shoptemp.entity.Result;
import org.example.shoptemp.entity.User;
import org.example.shoptemp.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 商品控制器
 * @author czx
 * @date 2021/8/6
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping
    public Result create(@RequestBody Goods goods) {
        User user = (User) request.getSession().getAttribute("user");
        //判断是不是商家，不是商家就不能添加商品
        if (user.getType() != 1) {
            return Result.fail("不是商家，不能添加商品");
        }
        goods.setUserid(user.getId());  //设置商家id
        goodsService.save(goods);
        return Result.success();
    }
}
