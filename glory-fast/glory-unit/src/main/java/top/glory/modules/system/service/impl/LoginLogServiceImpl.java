package top.glory.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import top.glory.common.utils.IpAddrUtils;
import top.glory.common.utils.ResponseResult;
import top.glory.modules.system.IpService;
import top.glory.modules.system.LoginLogService;
import top.glory.modules.system.RoleService;
import top.glory.modules.system.entity.SysLoginLog;
import top.glory.modules.system.entity.SysRole;
import top.glory.modules.system.entity.SysUser;
import top.glory.modules.system.mapper.LoginLogMapper;
import top.glory.modules.system.mapper.RoleMapper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Description: 登录日志
 * @author 春秋
 * @Date: 2020年4月15日
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, SysLoginLog> implements LoginLogService {
    @Resource
    private IpService ipService;

    @Override
    public ResponseResult saveLoginLog(HttpServletRequest request, SysLoginLog loginLog){
//        SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        String ip = IpAddrUtils.getRemoteAddr(request);
        loginLog.setLoginIp(ip);
        String ipArea = ipService.getIpArea(ip);
        loginLog.setLoginAddr(ipArea);
        loginLog.setLoginTime(new Date());
        loginLog.setLoginName(loginLog.getLoginName());
        loginLog.setUsername(loginLog.getUsername());
        super.save(loginLog);

        return ResponseResult.ok("");
    }
}
