package top.glory.modules.generate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.glory.common.utils.ResponseResult;
import top.glory.modules.generate.entity.GenTableField;
import top.glory.modules.generate.entity.GenTableInfo;

public interface GenTableFieldService extends IService<GenTableField> {

    ResponseResult saveField(GenTableInfo tableInfo);
}
