package org.example.shoptemp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.shoptemp.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface UserService extends IService<User> {

    User login(String username, String pass);

    /**
     * 分页查询
     * @param page
     * @param status
     * @return
     */
    Page<User> listByPage(Page<User> page, String status);
}
