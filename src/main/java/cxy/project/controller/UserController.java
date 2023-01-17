package cxy.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cxy.project.common.Result;
import cxy.project.entity.User;
import cxy.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Result<List<User>> getUserList() {
        List<User> userList = userService.list();

        return Result.success(userList);
    }

    @PostMapping
    public Result<String> addUser(@RequestBody User user) {
        boolean save = userService.save(user);

        if (save) {
            return Result.success("add user success");
        } else {
            return Result.error("add user fail");
        }
    }

    @GetMapping("/info")
    public Result<User> isLogin(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        log.info(userId.toString());
        User user = userService.getById(userId);
        return Result.success(user);
    }


    @PostMapping("/login")
    public Result<String> login(@RequestBody Map map, HttpSession session) {

        String password = map.get("password").toString();

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPassword, password);

        User user = userService.getOne(queryWrapper);
        if (user != null) {
            log.info(user.toString());
            session.setAttribute("userId", user.getId());
            session.setAttribute("userName", user.getUsername());

            return Result.success("登录成功");
        } else {
            return Result.error("登录失败");
        }

    }

}
