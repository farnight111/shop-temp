package org.example.shoptemp.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.shoptemp.entity.User;
import org.example.shoptemp.service.UserService;
import org.example.shoptemp.mapper.UserMapper;
import org.example.shoptemp.utils.StringUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Override
    public User login(String username, String pass) {
        Wrapper<User> queryWrapper = Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, username)
                .eq(User::getPass, pass)
                .last("limit 1");
        return getOne(queryWrapper);
    }

    @Override
    public Page<User> listByPage(Page<User> page, String status) {
        List<Integer> statusList = StringUtil.split2IntegerList(status, ",");
        Wrapper<User> queryWrapper = Wrappers.<User>lambdaQuery()
                .in(User::getStatus, statusList);
        return page(page, queryWrapper);
    }
}




