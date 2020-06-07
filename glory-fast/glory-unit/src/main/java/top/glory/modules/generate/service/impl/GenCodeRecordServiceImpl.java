package top.glory.modules.generate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.glory.modules.generate.entity.GenCodeRecord;
import top.glory.modules.generate.service.GenCodeRecordService;
import top.glory.modules.generate.mapper.GenCodeRecordMapper;

@Service
public class GenCodeRecordServiceImpl extends ServiceImpl<GenCodeRecordMapper, GenCodeRecord> implements GenCodeRecordService {


}
