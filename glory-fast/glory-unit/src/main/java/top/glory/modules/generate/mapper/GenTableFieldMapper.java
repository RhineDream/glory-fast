package top.glory.modules.generate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.glory.modules.generate.entity.GenTableField;
import top.glory.modules.generate.entity.GenTableInfo;
import top.glory.modules.system.entity.SysTableField;

import java.util.List;

@Mapper
public interface GenTableFieldMapper extends BaseMapper<GenTableField> {

    List<SysTableField> getTableField(GenTableInfo tableInfo);
}
