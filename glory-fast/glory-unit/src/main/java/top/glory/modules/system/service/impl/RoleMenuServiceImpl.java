package top.glory.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import top.glory.modules.system.entity.SysMenu;
import top.glory.modules.system.entity.SysRoleMenu;
import top.glory.modules.system.mapper.MenuMapper;
import top.glory.modules.system.mapper.RoleMenuMapper;
import top.glory.modules.system.service.MenuService;
import top.glory.modules.system.service.RoleMenuService;

import javax.annotation.Resource;

/**
 * @author 春秋
 * @Description: 用户管理
 * @Date: 2020年4月15日
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, SysRoleMenu> implements RoleMenuService {
    @Override
    public int deleteByRoleId(String roleId) {
        return this.baseMapper.deleteByRoleId(roleId);
    }
}
