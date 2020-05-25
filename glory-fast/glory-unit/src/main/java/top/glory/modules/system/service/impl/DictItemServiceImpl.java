package top.glory.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.glory.modules.system.DictItemService;
import top.glory.modules.system.MenuService;
import top.glory.modules.system.entity.SysDictItem;
import top.glory.modules.system.entity.SysPermission;
import top.glory.modules.system.mapper.DictItemMapper;
import top.glory.modules.system.mapper.MenuMapper;

import javax.annotation.Resource;

/**
 * @author 春秋
 * @Description: 用户管理
 * @Date: 2020年4月15日
 */
@Service
public class DictItemServiceImpl extends ServiceImpl<DictItemMapper, SysDictItem> implements DictItemService {

}
