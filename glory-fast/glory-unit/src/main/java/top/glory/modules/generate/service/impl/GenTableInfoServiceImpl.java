package top.glory.modules.generate.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.glory.common.utils.ResponseResult;
import top.glory.common.utils.StringUtil;
import top.glory.modules.generate.entity.GenTableInfo;
import top.glory.modules.generate.service.GenTableFieldService;
import top.glory.modules.generate.service.GenTableInfoService;
import top.glory.modules.generate.mapper.GenTableInfoMapper;
import top.glory.modules.system.entity.SysTable;

import javax.annotation.Resource;

@Service
public class GenTableInfoServiceImpl extends ServiceImpl<GenTableInfoMapper, GenTableInfo> implements GenTableInfoService {

    @Resource
    private GenTableInfoMapper genCodeTableMapper;
    @Resource
    private GenTableFieldService genTableFieldService;

    @Override
    public IPage<SysTable> getTablePageList(Page<SysTable> page, SysTable table) {
        return page.setRecords(genCodeTableMapper.getTablePageList(page, table));
    }

    @Override
    public ResponseResult saveTableInfo(GenTableInfo tableInfo) {
        //校验重复
        int exist = genCodeTableMapper.getByTableName(tableInfo);
        if(exist == 1){
            return ResponseResult.fail(500,"此表已存在，不能重复添加");
        }

        tableInfo.setClazzName(StringUtil.underline2Camel(tableInfo.getTableName(),true));
        //保存主表信息
        boolean flag = super.save(tableInfo);

        //保存字段信息
        if(flag){
            ResponseResult res = genTableFieldService.saveField(tableInfo);
            return res;
        }

        return ResponseResult.ok();
    }


}
