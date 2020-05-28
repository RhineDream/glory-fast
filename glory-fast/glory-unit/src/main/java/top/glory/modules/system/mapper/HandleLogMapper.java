package top.glory.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.glory.modules.system.entity.SysHandleLog;

/**
 * @Description: 操作日志
 * @Author: 春秋
 * @Date: 2020年4月15日
 */
@Mapper
public interface HandleLogMapper extends BaseMapper<SysHandleLog> {

}
