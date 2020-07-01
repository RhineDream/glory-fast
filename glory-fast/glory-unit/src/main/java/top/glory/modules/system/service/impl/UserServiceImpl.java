package top.glory.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.glory.common.utils.ResponseResult;
import top.glory.common.utils.UserUtils;
import top.glory.modules.system.entity.SysUserRole;
import top.glory.modules.system.mapper.UserRoleMapper;
import top.glory.modules.system.service.UserRoleService;
import top.glory.modules.system.service.UserService;
import top.glory.modules.system.entity.LoginUser;
import top.glory.modules.system.entity.SysUser;
import top.glory.modules.system.mapper.UserMapper;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @Description: 用户管理
 * @author 春秋
 * @Date: 2020年4月15日
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleService userRoleService;


    @Override
    public boolean save(SysUser user) {
        boolean save = super.save(user);
        this.updateUserRoleInfo(user);
        return save;
    }

    @Override
    public boolean updateById(SysUser user) {
        this.updateUserRoleInfo(user);
        return super.updateById(user);
    }

    /**
     * 处理用户角色中间表
     */
    private void updateUserRoleInfo(SysUser user) {
        //删除原有的角色
        int flag = userRoleService.deleteRoleByUserId(user.getId());

        String[] roles = user.getRoles();
        List<SysUserRole> list = Lists.newArrayList();
        for (String roleId : roles) {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleId);
            list.add(userRole);
        }
        userRoleService.saveBatch(list);
    }

    @Override
    public IPage<SysUser> getPage(Page<SysUser> page, QueryWrapper<SysUser> queryWrapper) {
        List<OrderItem> orderItems = Lists.newArrayList();
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("create_time");
        orderItem.setAsc(false);
        orderItems.add(orderItem);
        page.setOrders(orderItems);
        IPage<SysUser> pageInfo = super.page(page, queryWrapper);
        for (SysUser user : pageInfo.getRecords()) {
            String userId = user.getId();
            List<String> list = userRoleService.getRoleIdsByUserId(userId);
            String[] roles =list.stream().map(var -> String.valueOf(var)).toArray(String[]::new);
            user.setRoles(roles);
        }
        return pageInfo;
    }

    @Override
    public SysUser getUserByLoginName(LoginUser loginUser) {
        return userMapper.getUserByLoginName(loginUser);
    }

    @Override
    public Set<String> getUserRolesSet(String username) {
        return null;
    }

    @Override
    public Set<String> getUserPermissionsSet(String username) {
        return null;
    }

    @Override
    public ResponseResult importUser(List<SysUser> listUser) {
        boolean flag = super.saveBatch(listUser);
        return ResponseResult.ok("导入成功");
    }
}
