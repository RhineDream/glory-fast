package top.glory.common.utils;

import org.apache.shiro.SecurityUtils;
import top.glory.modules.system.entity.SysUser;

public class UserUtils {

    public static SysUser getCurrentUser() {
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if(sysUser == null ){
            sysUser = new SysUser();
        }
        return sysUser;
    }
}
