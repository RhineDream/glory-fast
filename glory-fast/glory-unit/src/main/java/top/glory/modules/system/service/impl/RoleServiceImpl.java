package top.glory.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.glory.common.utils.ResponseResult;
import top.glory.modules.system.service.RoleService;
import top.glory.modules.system.entity.SysRole;
import top.glory.modules.system.mapper.RoleMapper;

import javax.annotation.Resource;

/**
 * @Description: 用户管理
 * @author 春秋
 * @Date: 2020年4月15日
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, SysRole> implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public ResponseResult checkRoleCode(SysRole role) {
        SysRole roleByCode = this.roleMapper.getRoleByCode(role);
        if(roleByCode != null){
            return ResponseResult.build(400,"角色编码不能重复");
        }
        return ResponseResult.ok("角色编码可用");
    }
}
