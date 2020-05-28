package top.glory.config.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.StopWatch;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.glory.common.annotation.HandleLog;
import top.glory.common.utils.GsonUtils;
import top.glory.modules.system.DebugLogService;
import top.glory.modules.system.HandleLogService;
import top.glory.modules.system.entity.SysHandleLog;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;


@Aspect
@Component
@Slf4j
public class DebugLogAspect {

    @Resource
    private DebugLogService debugLogService;

    // 定义切点Pointcut
    @Pointcut("execution(public * top.glory.modules..*.*Service.*(..))")
    public void excudeService() {
    }

//    @Before(value = "")
//    public void before(JoinPoint joinPoint) {
//
//    }

//    @AfterReturning
//    public void AfterReturning(JoinPoint joinPoint, Object retValue) {
//    }

    @AfterThrowing(pointcut = "excudeService()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        try {
            System.out.println("捕获到异常=======>"+ex);
            debugLogService.handleLog(joinPoint, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public Log handleLog(JoinPoint joinPoint,Object retValue, Exception ex, String optName) {
//        try {
//
//            StringBuilder content = new StringBuilder();
//
//            String classType = joinPoint.getTarget().getClass().getName();
//            Class<?> clazz = Class.forName(classType);
//            String clazzName = clazz.getName();
//
//            String methodName = joinPoint.getSignature().getName();
//            boolean ret = filterMethodName(methodName);
//            if(ret){
//                String[] paramNames = getFieldsName(this.getClass(), clazzName, methodName);
//                String param = writeLogInfo(paramNames, joinPoint);
//                content.append("param:" + param);
//                if(retValue!=null && !retValue.equals("")){
//                    String retStr = getObjectToStr(retValue);
//                    content.append( ";retValue:"+retStr);
//                }
//            }else{
//                if(ex==null){
//                    return null;
//                }
//            }
//
//            Log log = new Log();
//            if(ex!=null){
//                content.append(";ex:"+StringUtil.getExceptionAllinfo(ex));
//                log.setIsException(1);
//            }else{
//                log.setIsException(0);
//            }
//
//
//            //(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"))
//            //Logger logger = LoggerFactory.getLogger(clazzName);
//            //logger.info("clazzName: " + clazzName + ", methodName:"+ methodName + ", content);
//            log.setLoginId(null);
//            log.setLoginName(null);
//            log.setMethodName(methodName);
//            log.setClazzName(clazzName);
//            log.setContent(content.toString());
//            log.setOptName(optName);
//            log.setType(1);
//            return log;
//
//        } catch (Exception exp) {
//            exp.printStackTrace();
//        }
//
//        return null;
//    }

}
