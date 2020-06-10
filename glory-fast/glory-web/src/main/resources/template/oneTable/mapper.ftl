package ${(genCodeRecord.packageName)!""}.${(genCodeRecord.moduleName)!""}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ${(genCodeRecord.packageName)!""}.${(genCodeRecord.moduleName)!""}.entity.${(tableInfo.clazzName)!""};

/**
 * @author ${(genCodeRecord.moduleAuthor)!""}
 * @Description: ${(genCodeRecord.moduleDesc)!""} Dao接口
 * @Date: ${(currDate)!""}
 */

@Mapper
public interface ${(tableInfo.clazzName)!""}Mapper extends BaseMapper<${(tableInfo.clazzName)!""}> {

}
