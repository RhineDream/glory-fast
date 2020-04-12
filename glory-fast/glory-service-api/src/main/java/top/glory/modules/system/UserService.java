package top.glory.modules.system;

import com.baomidou.mybatisplus.extension.service.IService;
import top.glory.modules.system.entity.LoginUser;
import top.glory.modules.system.entity.User;

public interface UserService extends IService<User> {

    User getUserByUsername(LoginUser loginUser);

}
