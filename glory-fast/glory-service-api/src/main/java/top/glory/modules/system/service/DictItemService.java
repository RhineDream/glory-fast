package top.glory.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.glory.modules.system.entity.SysDict;
import top.glory.modules.system.entity.SysDictItem;
import top.glory.modules.system.entity.SysPermission;

import java.util.List;

/**
 * @author 春秋
 * @Description: 用户管理
 * @Date: 2020年4月15日
 */
public interface DictItemService extends IService<SysDictItem> {

    List<SysDictItem> getDictItemList(SysDict dict);
}
