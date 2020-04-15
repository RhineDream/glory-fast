package top.glory.modules.system;

import com.baomidou.mybatisplus.extension.service.IService;
import top.glory.modules.system.entity.LoginUser;
import top.glory.modules.system.entity.SysUser;
import top.glory.modules.system.entity.User;

/**
 * @Description: 用户管理
 * @author 春秋
 * @Date: 2020年4月15日
 */
public interface UserService extends IService<SysUser> {

    User getUserByUsername(LoginUser loginUser);

}
