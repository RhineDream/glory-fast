package top.glory.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.aspectj.lang.JoinPoint;
import top.glory.modules.system.entity.SysDebugLog;

/**
 * @author 春秋
 * @Description: 用户管理
 * @Date: 2020年4月15日
 */
public interface DebugLogService extends IService<SysDebugLog> {

    SysDebugLog handleLog(JoinPoint joinPoint, Object o, Exception ex);
}
