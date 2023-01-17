package cxy.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import cxy.project.common.Result;
import cxy.project.entity.Bonus;
import cxy.project.entity.BonusUser;
import cxy.project.entity.UserBonusNum;
import cxy.project.service.BonusService;
import cxy.project.service.BonusUserService;
import cxy.project.service.UserBonusNumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bonus")
public class BonusController {

    @Autowired
    private BonusService bonusService;

    @Autowired
    private BonusUserService bonusUserService;

    @Autowired
    private UserBonusNumService userBonusNumService;

    @GetMapping
    public Result<List<Bonus>> getBonusList() {
        List<Bonus> list = bonusService.list();

        return Result.success(list);
    }

    @PostMapping
    public Result<String> addBonus(@RequestBody Bonus bonus) {
        boolean save = bonusService.save(bonus);
        if (save) {
            return Result.success("save bonus");
        } else {
            return Result.error("save bonus fail");
        }
    }

    @PostMapping("/map")
    public Result<String> addBonusToUser(@RequestBody Bonus bonus, HttpSession session) {
        log.info(bonus.toString());
        log.info(session.getAttribute("userId").toString());
        log.info(session.getAttribute("userName").toString());

        BonusUser bonusUser = new BonusUser();
        bonusUser.setBonusId(bonus.getId());
        bonusUser.setUserId((Long) session.getAttribute("userId"));

        boolean save = bonusUserService.save(bonusUser);
        if (save) {
            return Result.success("save bonus user");
        } else {
            return Result.error("save bonus user fail");
        }
    }

    @GetMapping("/num")
    public Result<UserBonusNum> getBonusNum(HttpSession session) {
        String userId = session.getAttribute("userId").toString();
        log.info(userId);

        LambdaQueryWrapper<UserBonusNum> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserBonusNum::getUserId, userId);
        UserBonusNum userBonusNum = userBonusNumService.getOne(queryWrapper);

        return Result.success(userBonusNum);
    }

    @PostMapping("/num")
    public Result<String> updateBonusNum(@RequestBody UserBonusNum userBonusNum, HttpSession session) {
        Integer num = userBonusNum.getBonusNum();
        log.info(userBonusNum.toString());
        log.info(String.valueOf(num));

        Long userId = (Long) session.getAttribute("userId");

        userBonusNum.setUserId(userId);

        LambdaQueryWrapper<UserBonusNum> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserBonusNum::getUserId, userId);

        int count = userBonusNumService.count(queryWrapper);
        if (count == 0) {
            userBonusNum.setBonusNum(num);
            userBonusNumService.save(userBonusNum);
        } else {
            LambdaUpdateWrapper<UserBonusNum> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.eq(UserBonusNum::getUserId, userId)
                    .set(UserBonusNum::getBonusNum, num);
            userBonusNumService.update(null, lambdaUpdateWrapper);
        }
        return Result.success("add user bonus num");
    }

}
