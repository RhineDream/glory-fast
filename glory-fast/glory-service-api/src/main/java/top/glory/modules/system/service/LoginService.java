package top.glory.modules.system.service;

import top.glory.modules.system.entity.LoginUser;

public interface LoginService {

    LoginUser doLogin(LoginUser loginUser);
}
