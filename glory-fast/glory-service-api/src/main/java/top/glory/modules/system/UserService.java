package top.glory.modules.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.glory.modules.system.entity.LoginUser;
import top.glory.modules.system.entity.SysUser;
import top.glory.modules.system.entity.User;

import java.util.Set;

/**
 * @Description: 用户管理
 * @author 春秋
 * @Date: 2020年4月15日
 */
public interface UserService extends IService<SysUser> {

    IPage<SysUser> getPage(Page<SysUser> sysUserPage, QueryWrapper<SysUser> queryWrapper);

    SysUser getUserByLoginName(LoginUser loginUser);

    Set<String> getUserRolesSet(String username);

    Set<String> getUserPermissionsSet(String username);


}
