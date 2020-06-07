package top.glory.modules.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.glory.modules.system.service.LoginService;

import javax.annotation.Resource;

@RequestMapping("/api/user/")
@RestController
public class UserController {

    @Resource
    private LoginService loginService;



}
