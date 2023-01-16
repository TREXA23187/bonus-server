package cxy.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cxy.project.entity.BonusUser;
import cxy.project.mapper.BonusUserMapper;
import cxy.project.service.BonusUserService;
import org.springframework.stereotype.Service;

@Service
public class BonusUserServiceImpl extends ServiceImpl<BonusUserMapper, BonusUser> implements BonusUserService {

}
