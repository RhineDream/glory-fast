package top.glory.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.glory.common.utils.ResponseResult;
import top.glory.modules.system.entity.SysRole;

/**
 * @Description: 用户管理
 * @author 春秋
 * @Date: 2020年4月15日
 */
public interface RoleService extends IService<SysRole> {

    ResponseResult checkRoleCode(SysRole role);
}
