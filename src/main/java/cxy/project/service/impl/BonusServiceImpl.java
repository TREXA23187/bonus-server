package cxy.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cxy.project.entity.Bonus;
import cxy.project.mapper.BonusMapper;
import cxy.project.service.BonusService;
import org.springframework.stereotype.Service;

@Service
public class BonusServiceImpl extends ServiceImpl<BonusMapper, Bonus> implements BonusService {

}
