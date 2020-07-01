package top.glory.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.glory.modules.system.entity.SysRole;
import top.glory.modules.system.entity.SysUserRole;

import java.util.List;

/**
 * @Description: 用户管理
 * @Author: 春秋
 * @Date: 2020年4月15日
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<SysUserRole> {

    int deleteRoleByUserId(@Param("userId") String userId);

    List<String> getRoleIdsByUserId(@Param("userId") String userId);
}
