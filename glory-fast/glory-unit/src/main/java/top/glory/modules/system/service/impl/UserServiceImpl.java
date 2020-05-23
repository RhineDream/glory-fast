package top.glory.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import top.glory.modules.system.UserService;
import top.glory.modules.system.entity.LoginUser;
import top.glory.modules.system.entity.SysUser;
import top.glory.modules.system.entity.User;
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

    @Override
    public IPage<SysUser> getPage(Page<SysUser> page, QueryWrapper<SysUser> queryWrapper) {
        List<OrderItem> orderItems = Lists.newArrayList();
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("create_time");
        orderItem.setAsc(false);
        orderItems.add(orderItem);
        page.setOrders(orderItems);
        return super.page(page, queryWrapper);
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
}
