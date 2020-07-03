package top.glory.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import top.glory.common.utils.ResponseResult;
import top.glory.modules.system.entity.SysRoleMenu;
import top.glory.modules.system.service.RoleMenuService;
import top.glory.modules.system.service.RoleService;
import top.glory.modules.system.entity.SysRole;
import top.glory.modules.system.mapper.RoleMapper;
import top.glory.modules.system.vo.SysRoleVo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 用户管理
 * @author 春秋
 * @Date: 2020年4月15日
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, SysRole> implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleMenuService roleMenuService;

    @Override
    public ResponseResult checkRoleCode(SysRole role) {
        SysRole roleByCode = this.roleMapper.getRoleByCode(role);
        if(roleByCode != null){
            return ResponseResult.build(400,"角色编码不能重复");
        }
        return ResponseResult.ok("角色编码可用");
    }

    @Override
    public ResponseResult grant(SysRoleVo role) {

        //删除之前的权限
        roleMenuService.deleteByRoleId(role.getId());

        String roleId = role.getId();
        List<String> menus = role.getMenus();
        List<SysRoleMenu> saveList = Lists.newArrayList();
        for (String menu : menus) {
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menu);
            saveList.add(roleMenu);
        }
        roleMenuService.saveBatch(saveList);
        return ResponseResult.ok("授权成功");
    }
}
