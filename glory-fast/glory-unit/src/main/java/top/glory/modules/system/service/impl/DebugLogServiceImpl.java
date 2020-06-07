package top.glory.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;
import top.glory.common.utils.DebugLogUtil;
import top.glory.common.utils.StringUtil;
import top.glory.common.utils.UserUtils;
import top.glory.modules.system.service.DebugLogService;
import top.glory.modules.system.entity.SysDebugLog;
import top.glory.modules.system.entity.SysUser;
import top.glory.modules.system.mapper.DebugLogMapper;

/**
 * @author 春秋
 * @Description: 登录日志
 * @Date: 2020年4月15日
 */
@Service
@Slf4j
public class DebugLogServiceImpl extends ServiceImpl<DebugLogMapper, SysDebugLog> implements DebugLogService {


    @Override
    public SysDebugLog handleLog(JoinPoint joinPoint, Object retValue, Exception ex) {
        try {
            StringBuilder content = new StringBuilder();

            String classType = joinPoint.getTarget().getClass().getName();
            Class<?> clazz = Class.forName(classType);
            String clazzName = clazz.getName();

            String methodName = joinPoint.getSignature().getName();
            boolean ret = filterMethodName(methodName);
            if (ret) {
                String[] paramNames = DebugLogUtil.getFieldsName(this.getClass(), clazzName, methodName);
                String param = DebugLogUtil.writeLogInfo(paramNames, joinPoint);
                content.append("param:" + param);
                if (retValue != null && !retValue.equals("")) {
                    String retStr = DebugLogUtil.getObjectToStr(retValue);
                    content.append(";retValue:" + retStr);
                }
            } else {
                if (ex == null) {
                    return null;
                }
            }

            SysDebugLog debugLog = new SysDebugLog();
            if (ex != null) {
                content.append(";ex:" + StringUtil.getExceptionAllinfo(ex));
                debugLog.setIfException("1");
            } else {
                debugLog.setIfException("2");
            }
            SysUser currentUser = UserUtils.getCurrentUser();

            debugLog.setLoginName(currentUser.getLoginName());
            debugLog.setUsername(currentUser.getUsername());
            debugLog.setPostMethod(methodName);
            debugLog.setClazzName(clazzName);
            debugLog.setErrorData(content.toString());

            //保存异常日志
            super.save(debugLog);

            return debugLog;

        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return null;
    }

    private boolean filterMethodName(String methodName) {
        boolean ret = true;
        methodName = methodName.toLowerCase();
        if (methodName.indexOf("get") != -1) {
            ret = false;
        } else if (methodName.indexOf("find") != -1) {
            ret = false;
        } else if (methodName.indexOf("load") != -1) {
            ret = false;
        } else if (methodName.indexOf("query") != -1) {
            ret = false;
        } else if (methodName.indexOf("list") != -1) {
            ret = false;
        } else if (methodName.indexOf("export") != -1) {
            ret = false;
        } else if (methodName.indexOf("import") != -1) {
            ret = false;
        } else if (methodName.indexOf("upload") != -1) {
            ret = false;
        }
        return ret;
    }

}
