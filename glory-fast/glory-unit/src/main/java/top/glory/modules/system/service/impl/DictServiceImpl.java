package top.glory.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.glory.common.constant.CacheConstant;
import top.glory.modules.system.service.DictService;
import top.glory.modules.system.entity.SysDict;
import top.glory.modules.system.mapper.DictMapper;

import javax.annotation.Resource;

/**
 * @Description: 用户管理
 * @author 春秋
 * @Date: 2020年4月15日
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, SysDict> implements DictService {

    @Resource
    private DictMapper dictMapper;

    @Override
    @Cacheable(value = CacheConstant.SYS_DICT_TABLE_CACHE)
    public String queryTableDictTextByKey(String table,String text,String code, String key) {
//		log.info("无缓存dictTable的时候调用这里！");
        return dictMapper.queryTableDictTextByKey(table,text,code,key);
    }

    @Override
    @Cacheable(value = CacheConstant.SYS_DICT_CACHE,key = "#code+':'+#key")
    public String queryDictTextByKey(String code, String key) {
//        log.info("无缓存dictText的时候调用这里！");
        return dictMapper.queryDictTextByKey(code, key);
    }
}
