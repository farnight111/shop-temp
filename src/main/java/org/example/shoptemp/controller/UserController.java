package org.example.shoptemp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
            if (user.getStatus() != 1) {
                return Result.fail("未通过审核");
            }
            request.getSession().setAttribute("user", user);
            final User clone = user.clone();
            clone.setPass(null);
            return Result.success(clone);
        } else {
            return Result.fail("用户名或密码错误");
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

    /**
     * 审核用户
     * @param userid
     * @param status 1审核通过  2不通过
     * @return
     */
    @PutMapping("/audit-user")
    public Result auditUser(Integer userid, Integer status,HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("user");
        if (currentUser==null) {
            return Result.fail("没登陆");
        }
        if (currentUser.getType() != 2) {
            return Result.fail("不是管理员，不允许审核");
        }
        User target = new User();
        target.setId(userid);
        target.setStatus(status);
        userService.updateById(target);
        return Result.success();
    }

    /**
     * 分页查询
     * @param size 每页大小
     * @param current 当前页数
     * @param status 过滤条件-用户审核状态 0未审核 1审核通过 2审核不通过,多个用逗号分隔
     * @return
     */
    @GetMapping("page")
    public Result<Page<User>> listByPage(Page<User> page, String status) {
        Page<User> resultPage = userService.listByPage(page, status);
        return Result.success(resultPage);
    }
}
