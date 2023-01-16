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

    @PostMapping("/login")
    public Result<String> login(@RequestBody Map map, HttpSession session) {

        String username = map.get("username").toString();
        String password = map.get("password").toString();

        Object codeInSession = session.getAttribute(username);

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPassword, password);

        User user = userService.getOne(queryWrapper);
        if (user != null) {
            log.info(user.toString());
            session.setAttribute("user", user.getId());

            return Result.success("login");
        } else {
            return Result.error("login error");
        }

    }

}
