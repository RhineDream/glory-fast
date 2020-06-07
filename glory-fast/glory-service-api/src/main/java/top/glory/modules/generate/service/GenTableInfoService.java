package top.glory.modules.generate.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.glory.modules.generate.entity.GenTableInfo;
import top.glory.modules.system.entity.SysTable;

public interface GenTableInfoService extends IService<GenTableInfo> {


    IPage<SysTable> getTablePageList(Page<SysTable> sysTablePage, SysTable table);
}
