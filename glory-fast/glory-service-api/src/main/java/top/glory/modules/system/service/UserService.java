package top.glory.modules.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.glory.common.utils.ResponseResult;
import top.glory.modules.system.entity.LoginUser;
import top.glory.modules.system.entity.SysUser;

import java.util.List;
import java.util.Set;

/**
 * @author 春秋
 * @Description: 用户管理
 * @Date: 2020年4月15日
 */
public interface UserService extends IService<SysUser> {

    IPage<SysUser> getPage(Page<SysUser> sysUserPage, QueryWrapper<SysUser> queryWrapper);

    SysUser getUserByLoginName(LoginUser loginUser);

    Set<String> getUserRolesSet(String username);

    Set<String> getUserPermissionsSet(String username);

    ResponseResult importUser(List<SysUser> listUser);
}
