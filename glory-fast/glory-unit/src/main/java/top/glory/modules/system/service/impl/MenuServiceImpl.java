package top.glory.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import top.glory.common.utils.StringUtil;
import top.glory.common.utils.UserUtils;
import top.glory.modules.system.entity.SysUser;
import top.glory.modules.system.entity.TreeSupportEntity;
import top.glory.modules.system.service.MenuService;
import top.glory.modules.system.entity.SysMenu;
import top.glory.modules.system.mapper.MenuMapper;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author 春秋
 * @Description: 用户管理
 * @Date: 2020年4月15日
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, SysMenu> implements MenuService {

    @Override
    public List<SysMenu> getUserMenuList() {
        SysUser currentUser = UserUtils.getCurrentUser();
        List<SysMenu> userMenuList = this.baseMapper.getUserMenuList(currentUser.getId());
        List<SysMenu> servicePlaces = TreeSupportEntity.list2tree(userMenuList, SysMenu::setChildren,
                (Predicate<SysMenu>) servicePlace ->
                        // parentId为空或者为-1的菜单则认为是根菜单
                        StringUtils.isEmpty(servicePlace.getParentId()) || servicePlace.getParentId().equals("-1")
                                || servicePlace.getParentId().equals("0"));
        return servicePlaces;
    }

    @Override
    public boolean save(SysMenu menu) {
        String parentId = menu.getParentId();
        if(StringUtils.isBlank(parentId)){
            menu.setParentId("0");
        }
        return super.save(menu);
    }

    @Override
    public boolean updateById(SysMenu menu) {
        String parentId = menu.getParentId();
        if(StringUtils.isBlank(parentId)){
            menu.setParentId("0");
        }
        return super.updateById(menu);
    }


}
