package top.glory.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.glory.modules.system.HandleLogService;
import top.glory.modules.system.entity.SysHandleLog;
import top.glory.modules.system.mapper.HandleLogMapper;

/**
 * @author 春秋
 * @Description: 登录日志
 * @Date: 2020年4月15日
 */
@Service
public class HandleLogServiceImpl extends ServiceImpl<HandleLogMapper, SysHandleLog> implements HandleLogService {
    @Override
    public void test(String s) {
        String[] split = s.split("");
        String s1 = split[3];
        int a = 1 / 0;
        System.out.println(s);
    }
}
