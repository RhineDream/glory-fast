package top.glory.modules.shiro.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import top.glory.common.utils.JwtUtil;
import top.glory.common.utils.PasswordUtil;
import top.glory.modules.shiro.vo.ResponseBean;
import top.glory.modules.system.UserService;
import top.glory.modules.system.entity.LoginUser;
import top.glory.modules.system.entity.SysUser;
import top.glory.modules.system.entity.User;

@RestController
public class TestWebController {
 
    private UserService userService;
 
    @Autowired
    public void setService(UserService userService) {
        this.userService = userService;
    }
 
    @PostMapping("/login")
    public ResponseBean login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        SysUser user = userService.getUserByUsername(new LoginUser(username, null));
        if(user==null) {
    		return new ResponseBean(200, "用户不存在！", JwtUtil.sign(username, user.getPassword()));
    	}
    	String passwordEncode = PasswordUtil.encrypt(username, password, user.getSalt());
        if (passwordEncode.equals(user.getPassword())) {
            return new ResponseBean(200, "Login success", JwtUtil.sign(username, user.getPassword()));
        } else {
            throw new UnauthorizedException();
        }
    }
 
    @GetMapping("/article")
    public ResponseBean article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new ResponseBean(200, "You are already logged in", null);
        } else {
            return new ResponseBean(200, "You are guest", null);
        }
    }
 
    @GetMapping("/require_auth")
    @RequiresAuthentication
    public ResponseBean requireAuth() {
        return new ResponseBean(200, "You are authenticated", null);
    }
 
    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public ResponseBean requireRole() {
        return new ResponseBean(200, "You are visiting require_role", null);
    }
 
    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
    public ResponseBean requirePermission() {
        return new ResponseBean(200, "You are visiting permission require edit,view", null);
    }
 
    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean unauthorized() {
        return new ResponseBean(401, "Unauthorized", null);
    }
}