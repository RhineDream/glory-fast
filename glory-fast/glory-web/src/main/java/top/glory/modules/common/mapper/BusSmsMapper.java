package top.glory.modules.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.glory.modules.common.entity.BusSms;
import top.glory.modules.contract.entity.SignContract;

/**
 * @author 春秋
 * @Description: 短信发送记录 Dao接口
 * @Date: 2020-07-19
 */

@Mapper
public interface BusSmsMapper extends BaseMapper<BusSms> {

}
