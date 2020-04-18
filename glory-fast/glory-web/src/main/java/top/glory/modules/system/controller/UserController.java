package top.glory.modules.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.glory.common.utils.GsonUtils;
import top.glory.common.utils.ResponseResult;
import top.glory.modules.system.LoginService;
import top.glory.modules.system.entity.LoginUser;
import top.glory.modules.system.vo.Role;
import top.glory.modules.system.vo.UserInfo;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/api/user/")
@RestController
public class UserController {

    @Resource
    private LoginService loginService;



}
