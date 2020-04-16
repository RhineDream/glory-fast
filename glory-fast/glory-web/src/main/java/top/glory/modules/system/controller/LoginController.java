package top.glory.modules.system.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.glory.common.utils.*;
import top.glory.modules.system.LoginService;
import top.glory.modules.system.UserService;
import top.glory.modules.system.entity.LoginUser;
import top.glory.modules.system.entity.SysUser;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/api/auth/")
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @Resource
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("login")
    public ResponseResult Login(@RequestBody LoginUser loginUser){

        String username = loginUser.getUsername();
        String password = loginUser.getPassword();


        //1. 校验用户是否有效
        SysUser sysUser = userService.getUserByUsername(new LoginUser(username, null));

        //校验用户有效性
//        result = sysUserService.checkUserIsEffective(sysUser);
//        if(!result.isSuccess()) {
//            return result;
//        }

        //2. 校验用户名或密码是否正确
        String userpassword = PasswordUtil.encrypt(username, password, sysUser.getSalt());
        String syspassword = sysUser.getPassword();
        if (!syspassword.equals(userpassword)) {
            return ResponseResult.fail(500,"用户名或密码错误");
        }

        //用户登录信息
        ResponseResult res = userInfo(sysUser);
//        sysBaseAPI.addLog("用户名: " + username + ",登录成功！", CommonConstant.LOG_TYPE_1, null);


//        LoginUser user = loginService.doLogin(loginUser);
//        Map<String,Object> map = new HashMap<>();
//        map.put("id", UUID.randomUUID());
//        map.put("name", "管理员");
//        map.put("username", loginUser.getUsername());
//        map.put("password", "");
//        map.put("avatar","https://gw.alipayobjects.com/zos/rmsportal/jZUIxmJycoymBprLOUbT.png");
//        map.put("status", 1);
//        map.put("telephone", "");
//        map.put("lastLoginIp", "27.154.74.117");
//        map.put("lastLoginTime", "1534837621348");
//        map.put("roleId", "admin");
//        map.put("token", "4291d7da9005377ec9aec4a71ea837f");

        return res;
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

    /**
     * 用户信息
     *
     * @param sysUser
     * @return
     */
    private ResponseResult userInfo(SysUser sysUser) {
        String syspassword = sysUser.getPassword();
        String username = sysUser.getUsername();
        // 生成token
        String token = JwtUtil.sign(username, syspassword);
        // 设置token缓存有效时间
        redisUtils.set(Constants.PREFIX_USER_TOKEN + token, token);
        redisUtils.expire(Constants.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME*2 / 1000);

        // 获取用户部门信息
        JSONObject obj = new JSONObject();
//        List<SysDepart> departs = sysDepartService.queryUserDeparts(sysUser.getId());
//        obj.put("departs", departs);
//        if (departs == null || departs.size() == 0) {
//            obj.put("multi_depart", 0);
//        } else if (departs.size() == 1) {
//            userService.updateUserDepart(username, departs.get(0).getOrgCode());
//            obj.put("multi_depart", 1);
//        } else {
//            obj.put("multi_depart", 2);
//        }
        obj.put("token", token);
        obj.put("userInfo", sysUser);
        return ResponseResult.ok("登录成功",obj);
    }
}
