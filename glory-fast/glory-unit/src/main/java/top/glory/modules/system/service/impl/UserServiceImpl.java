package top.glory.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.glory.modules.system.UserService;
import top.glory.modules.system.entity.LoginUser;
import top.glory.modules.system.entity.SysUser;
import top.glory.modules.system.entity.User;
import top.glory.modules.system.mapper.UserMapper;

import javax.annotation.Resource;

/**
 * @Description: 用户管理
 * @author 春秋
 * @Date: 2020年4月15日
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserByUsername(LoginUser loginUser) {
        return userMapper.getUserByUsername(loginUser);
    }
}
