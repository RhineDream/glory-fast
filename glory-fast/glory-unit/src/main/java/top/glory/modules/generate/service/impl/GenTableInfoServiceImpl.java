package top.glory.modules.generate.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.glory.modules.generate.GenTableInfo;
import top.glory.modules.generate.GenTableInfoService;
import top.glory.modules.generate.mapper.GenTableInfoMapper;
import top.glory.modules.system.entity.SysTable;

import javax.annotation.Resource;

@Service
public class GenTableInfoServiceImpl extends ServiceImpl<GenTableInfoMapper, GenTableInfo> implements GenTableInfoService {

    @Resource
    private GenTableInfoMapper genCodeTableMapper;

    @Override
    public IPage<SysTable> getTablePageList(Page<SysTable> page, SysTable table) {
        return page.setRecords(genCodeTableMapper.getTablePageList(page, table));
    }
}
