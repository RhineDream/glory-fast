package top.glory.modules.system.service.impl;

import org.springframework.stereotype.Service;
import top.glory.modules.system.LoginService;
import top.glory.modules.system.UserService;
import top.glory.modules.system.entity.LoginUser;
import top.glory.modules.system.entity.User;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserService userService;

    @Override
    public LoginUser doLogin(LoginUser loginUser) {
        User user = userService.getUserByUsername(loginUser);
        System.out.println(user);
        return loginUser;
    }
}
