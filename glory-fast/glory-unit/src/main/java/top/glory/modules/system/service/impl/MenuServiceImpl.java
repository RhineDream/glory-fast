package top.glory.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.glory.modules.system.MenuService;
import top.glory.modules.system.UserService;
import top.glory.modules.system.entity.LoginUser;
import top.glory.modules.system.entity.SysMenu;
import top.glory.modules.system.entity.SysUser;
import top.glory.modules.system.mapper.MenuMapper;
import top.glory.modules.system.mapper.UserMapper;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @Description: 用户管理
 * @author 春秋
 * @Date: 2020年4月15日
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, SysMenu> implements MenuService {

    @Resource
    private MenuMapper menuMapper;

}
