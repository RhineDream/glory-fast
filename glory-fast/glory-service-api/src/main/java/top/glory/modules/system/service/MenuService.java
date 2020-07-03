package top.glory.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.glory.modules.system.entity.SysMenu;
import top.glory.modules.system.entity.SysRole;

import java.util.List;

/**
 * @author 春秋
 * @Description: 用户管理
 * @Date: 2020年4月15日
 */
public interface MenuService extends IService<SysMenu> {

    List<SysMenu> getUserMenuList();

    List<String> getMenuListByRole(String roleId);
}
