package top.glory.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.glory.common.utils.ResponseResult;
import top.glory.modules.system.entity.SysRole;
import top.glory.modules.system.entity.SysUserRole;

import java.util.List;

/**
 * @author 春秋
 * @Description: 用户管理
 * @Date: 2020年4月15日
 */
public interface UserRoleService extends IService<SysUserRole> {

    int deleteRoleByUserId(String id);

    List<String> getRoleIdsByUserId(String userId);
}
