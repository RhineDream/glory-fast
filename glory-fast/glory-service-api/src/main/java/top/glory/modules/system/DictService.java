package top.glory.modules.system;

import com.baomidou.mybatisplus.extension.service.IService;
import top.glory.modules.system.entity.SysDict;

/**
 * @author 春秋
 * @Description: 用户管理
 * @Date: 2020年4月15日
 */
public interface DictService extends IService<SysDict> {

    String queryTableDictTextByKey(String table, String text, String code, String key);

    public String queryDictTextByKey(String code, String key);
}
