package top.glory.modules.system;

import com.baomidou.mybatisplus.extension.service.IService;
import top.glory.modules.system.entity.SysHandleLog;

/**
 * @author 春秋
 * @Description: 用户管理
 * @Date: 2020年4月15日
 */
public interface HandleLogService extends IService<SysHandleLog> {

    void test(String s);
}
