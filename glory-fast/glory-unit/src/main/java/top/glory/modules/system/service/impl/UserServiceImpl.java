package top.glory.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.glory.modules.system.UserService;
import top.glory.modules.system.entity.LoginUser;
import top.glory.modules.system.entity.User;
import top.glory.modules.system.mapper.UserMapper;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserByUsername(LoginUser loginUser) {
        return userMapper.getUserByUsername(loginUser);
    }
}
