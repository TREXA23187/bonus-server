package cxy.project.controller;


import cxy.project.common.Result;
import cxy.project.entity.Bonus;
import cxy.project.entity.BonusUser;
import cxy.project.service.BonusService;
import cxy.project.service.BonusUserService;
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

    @GetMapping
    public Result<List<Bonus>> getBonusList(){
        List<Bonus> list = bonusService.list();

        return Result.success(list);
    }

    @PostMapping
    public Result<String> addBonus(@RequestBody Bonus bonus){
        boolean save = bonusService.save(bonus);
        if(save){
            return Result.success("save bonus");
        }else {
            return Result.error("save bonus fail");
        }
    }

    @PostMapping("/map")
    public Result<String> addBonusToUser(@RequestBody Bonus bonus, HttpSession session){
        log.info(bonus.toString());
        log.info(session.getAttribute("userId").toString());
        log.info(session.getAttribute("userName").toString());

        BonusUser bonusUser = new BonusUser();
        bonusUser.setBonusId(bonus.getId());
        bonusUser.setUserId((Long) session.getAttribute("userId"));

        boolean save = bonusUserService.save(bonusUser);
        if(save){
            return Result.success("save bonus user");
        }else {
            return Result.error("save bonus user fail");
        }
    }

}
