package org.example.shoptemp.controller;

import org.example.shoptemp.entity.Result;
import org.example.shoptemp.entity.User;
import org.example.shoptemp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        userService.save(user);
        return Result.success();
    }

    @PostMapping("login")
    public Result login(String username, String pass, HttpServletRequest request) {
        User user = userService.login(username, pass);
        if (user != null) {
            request.getSession().setAttribute("user", user);
            final User clone = user.clone();
            clone.setPass(null);
            return Result.success(clone);
        } else {
            return Result.fail();
        }
    }

    /**
     * 改密码
     * @param oldPass
     * @param newPass
     * @param request
     * @return
     */
    @PutMapping("/pass")
    public Result changePass(String oldPass, String newPass, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user==null) {
            return Result.fail("没登陆");
        }
        if (!user.getPass().equals(oldPass)) {
            return Result.fail("旧密码错误");
        }
        user.setPass(newPass);
        userService.updateById(user);
        return Result.success();
    }
}
