package top.glory.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.glory.modules.system.entity.LoginUser;
import top.glory.modules.system.entity.User;

/**
 * @Description: 经销店库房信息
 * @Author: Rhine
 * @Date: 2019-08-21
 * @Version: V1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User getUserByUsername(LoginUser loginUser);
}
