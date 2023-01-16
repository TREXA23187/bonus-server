package cxy.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cxy.project.common.Result;
import cxy.project.entity.Bonus;
import cxy.project.entity.BonusUserMap;
import cxy.project.entity.User;
import cxy.project.service.BonusService;
import cxy.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/bonus")
public class BonusController {

    @Autowired
    private BonusService bonusService;

    @PostMapping
    public Result<String> addUser(@RequestBody Object bonus){
        log.info(bonus.toString());

        return Result.success("add bonus to user");
    }

}
