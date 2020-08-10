package top.glory.modules.file.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.glory.common.utils.ResponseResult;
import top.glory.modules.file.entity.BusFile;
import top.glory.modules.file.mapper.BusFileMapper;
import top.glory.modules.file.service.BusFileService;

/**
 * @author 春秋
 * @Description: 文件管理 Service实现类
 * @Date: 2020-07-25
 */


@Service
@Transactional
public class BusFileServiceImpl extends ServiceImpl<BusFileMapper, BusFile> implements BusFileService {
    @Override
    public ResponseResult saveFile(BusFile busFile) {
        boolean flag = super.save(busFile);
        if (flag) {
            return ResponseResult.ok("保存成功");
        }
        return ResponseResult.fail(500, "保存失败");
    }
}
