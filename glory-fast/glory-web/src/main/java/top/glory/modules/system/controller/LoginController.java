package top.glory.modules.system.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.glory.common.utils.ResponseResult;
import top.glory.modules.system.LoginService;
import top.glory.modules.system.entity.LoginUser;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/api/auth/")
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @RequestMapping("login")
    public ResponseResult Login(@RequestBody LoginUser loginUser){

        LoginUser user = loginService.doLogin(loginUser);
        Map<String,Object> map = new HashMap<>();
        map.put("id", UUID.randomUUID());
        map.put("name", "管理员");
        map.put("username", loginUser.getUsername());
        map.put("password", "");
        map.put("avatar","https://gw.alipayobjects.com/zos/rmsportal/jZUIxmJycoymBprLOUbT.png");
        map.put("status", 1);
        map.put("telephone", "");
        map.put("lastLoginIp", "27.154.74.117");
        map.put("lastLoginTime", "1534837621348");
        map.put("roleId", "admin");
        map.put("token", "4291d7da9005377ec9aec4a71ea837f");

        return ResponseResult.ok(map);
    }

    @RequestMapping("logout")
    public ResponseResult logout(){

        Map<String,Object> map = new HashMap<>();
        map.put("id", UUID.randomUUID());
        map.put("name", "管理员");
        map.put("username", "!1");
        map.put("password", "");
        map.put("avatar","https://gw.alipayobjects.com/zos/rmsportal/jZUIxmJycoymBprLOUbT.png");
        map.put("status", 1);
        map.put("telephone", "");
        map.put("lastLoginIp", "27.154.74.117");
        map.put("lastLoginTime", "1534837621348");
        map.put("roleId", "admin");
        map.put("token", "4291d7da9005377ec9aec4a71ea837f");

        return ResponseResult.ok(map);
    }
}
