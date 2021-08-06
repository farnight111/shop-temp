package org.example.shoptemp.service;

import org.example.shoptemp.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface UserService extends IService<User> {

    User login(String username, String pass);

}
