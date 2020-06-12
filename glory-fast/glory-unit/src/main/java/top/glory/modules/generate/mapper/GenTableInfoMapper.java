package top.glory.modules.generate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import top.glory.modules.generate.entity.GenTableInfo;
import top.glory.modules.system.entity.SysTable;

import java.util.List;

@Mapper
public interface GenTableInfoMapper extends BaseMapper<GenTableInfo> {

    List<SysTable> getTablePageList(IPage<SysTable> page, SysTable table);

    int getByTableName(GenTableInfo tableInfo);
}
