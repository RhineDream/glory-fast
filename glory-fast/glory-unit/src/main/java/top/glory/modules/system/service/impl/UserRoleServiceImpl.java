package top.glory.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.glory.common.utils.ResponseResult;
import top.glory.modules.system.entity.SysRole;
import top.glory.modules.system.entity.SysUserRole;
import top.glory.modules.system.mapper.RoleMapper;
import top.glory.modules.system.mapper.UserRoleMapper;
import top.glory.modules.system.service.RoleService;
import top.glory.modules.system.service.UserRoleService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 用户管理
 * @author 春秋
 * @Date: 2020年4月15日
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, SysUserRole> implements UserRoleService {

    @Override
    public int deleteRoleByUserId(String userId) {
        return this.baseMapper.deleteRoleByUserId(userId);
    }

    @Override
    public List<String> getRoleIdsByUserId(String userId) {
        return this.baseMapper.getRoleIdsByUserId(userId);
    }
}
