package top.glory.modules.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.glory.common.utils.ResponseResult;
import top.glory.modules.file.entity.BusFile;

/**
 * @author 春秋
 * @Description: 文件管理 Service接口
 * @Date: 2020-07-25
 */

public interface BusFileService extends IService<BusFile> {

    ResponseResult saveFile(BusFile busFile);
}
