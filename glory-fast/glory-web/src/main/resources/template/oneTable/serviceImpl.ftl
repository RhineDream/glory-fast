package ${(genCodeRecord.packageName)!""}.${(genCodeRecord.moduleName)!""}.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ${(genCodeRecord.packageName)!""}.${(genCodeRecord.moduleName)!""}.entity.${(tableInfo.clazzName)!""};
import ${(genCodeRecord.packageName)!""}.${(genCodeRecord.moduleName)!""}.service.${(tableInfo.clazzName)!""}Service;
import ${(genCodeRecord.packageName)!""}.${(genCodeRecord.moduleName)!""}.mapper.${(tableInfo.clazzName)!""}Mapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ${(genCodeRecord.moduleAuthor)!""}
 * @Description: ${(genCodeRecord.moduleDesc)!""} Service实现类
 * @Date: ${(currDate)!""}
 */


@Service
@Transactional
public class ${(tableInfo.clazzName)!""}ServiceImpl extends ServiceImpl<${(tableInfo.clazzName)!""}Mapper, ${(tableInfo.clazzName)!""}> implements ${(tableInfo.clazzName)!""}Service {

}
