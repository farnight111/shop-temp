package org.example.shoptemp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.shoptemp.entity.Goods;
import org.example.shoptemp.entity.Result;
import org.example.shoptemp.entity.User;
import org.example.shoptemp.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        goods.setOwnerId(user.getId());  //设置商家id
        goodsService.save(goods);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Goods goods) {
        User user = (User) request.getSession().getAttribute("user");
        //判断是不是商家，不是商家就不能添加商品
        if (user.getType() != 1) {
            return Result.fail("不是商家，不能修改商品");
        }
        goodsService.updateById(goods);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        goodsService.removeById(id);
        return Result.success();
    }

    /**
     * 分页
     * @param size 每页的数量
     * @param current 当前页数
     * @return
     */
    @GetMapping("/page")
    public Result<List<Goods>> listByPage(Page<Goods> page) {
        List<Goods> resultPage = goodsService.list();
        return Result.success(resultPage);
    }

    @GetMapping
    public Result<Goods> getById(Integer id) {
        Goods goods = goodsService.getById(id);
        return Result.success(goods);
    }
}
