package cxy.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cxy.project.entity.User;
import cxy.project.mapper.UserMapper;
import cxy.project.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
