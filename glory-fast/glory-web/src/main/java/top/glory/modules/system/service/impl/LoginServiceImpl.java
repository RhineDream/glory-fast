package top.glory.modules.system.service.impl;

import org.springframework.stereotype.Service;
import top.glory.modules.system.service.LoginService;
import top.glory.modules.system.service.UserService;
import top.glory.modules.system.entity.LoginUser;
import top.glory.modules.system.entity.SysUser;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserService userService;

    @Override
    public LoginUser doLogin(LoginUser loginUser) {
        SysUser user = userService.getUserByLoginName(loginUser);
        System.out.println(user);
        return loginUser;
    }
}
