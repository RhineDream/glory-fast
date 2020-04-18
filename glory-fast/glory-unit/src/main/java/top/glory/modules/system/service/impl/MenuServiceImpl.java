package top.glory.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.glory.modules.system.MenuService;
import top.glory.modules.system.entity.SysPermission;
import top.glory.modules.system.mapper.MenuMapper;

import javax.annotation.Resource;

/**
 * @author 春秋
 * @Description: 用户管理
 * @Date: 2020年4月15日
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, SysPermission> implements MenuService {

    @Resource
    private MenuMapper menuMapper;

}
