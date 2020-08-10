package top.glory.modules.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.glory.modules.common.entity.BusSms;
import top.glory.modules.common.mapper.BusSmsMapper;
import top.glory.modules.common.service.BusSmsService;

/**
 * @author 春秋
 * @Description: 短信发送记录 Service实现类
 * @Date: 2020-07-19
 */


@Service
@Transactional
public class BusSmsServiceImpl extends ServiceImpl<BusSmsMapper, BusSms> implements BusSmsService {

}
