package top.glory.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.glory.modules.system.entity.SysDict;

/**
 * @Description: 用户管理
 * @Author: 春秋
 * @Date: 2020年4月15日
 */
@Mapper
public interface DictMapper extends BaseMapper<SysDict> {
    public String queryTableDictTextByKey(@Param("table") String table,@Param("text") String text,@Param("code") String code,@Param("key") String key);

    public String queryDictTextByKey(@Param("code") String code, @Param("key") String key);

}
