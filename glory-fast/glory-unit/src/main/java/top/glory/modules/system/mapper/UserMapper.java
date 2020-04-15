package top.glory.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.glory.modules.system.entity.LoginUser;
import top.glory.modules.system.entity.SysUser;
import top.glory.modules.system.entity.User;

/**
 * @Description: 用户管理
 * @Author: 春秋
 * @Date: 2020年4月15日
 */
@Mapper
public interface UserMapper extends BaseMapper<SysUser> {

    User getUserByUsername(LoginUser loginUser);

}
