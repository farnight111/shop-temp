package org.example.shoptemp.controller;

import org.example.shoptemp.entity.Result;
import org.example.shoptemp.entity.User;
import org.example.shoptemp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author czx
 * @date 2021/8/5
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public Result create(@RequestBody User user) {
        userService.save(user);
        return Result.success();
    }

    @PostMapping("login")
    public Result login(String username, String pass, HttpServletRequest request) {
        User user = userService.login(username, pass);
        if (user != null) {
            request.getSession().setAttribute("user", user);
            return Result.success();
        } else {
            return Result.fail();
        }
    }
}
