package top.glory.modules.generate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.glory.common.utils.ResponseResult;
import top.glory.modules.generate.entity.GenCodeRecord;

public interface GenCodeRecordService extends IService<GenCodeRecord> {

    ResponseResult createCodeFile(GenCodeRecord codeRecord);
}
