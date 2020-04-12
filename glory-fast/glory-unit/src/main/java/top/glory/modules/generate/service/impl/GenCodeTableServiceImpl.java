package top.glory.modules.generate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.glory.modules.generate.GenCodeTable;
import top.glory.modules.generate.GenCodeTableService;
import top.glory.modules.generate.mapper.GenCodeTableMapper;

@Service
public class GenCodeTableServiceImpl extends ServiceImpl<GenCodeTableMapper, GenCodeTable> implements GenCodeTableService {

}
