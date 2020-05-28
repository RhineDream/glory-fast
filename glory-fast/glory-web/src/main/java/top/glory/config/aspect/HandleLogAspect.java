package top.glory.config.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.glory.common.annotation.HandleLog;
import top.glory.common.utils.GsonUtils;
import top.glory.common.utils.UserUtils;
import top.glory.modules.system.HandleLogService;
import top.glory.modules.system.entity.SysHandleLog;
import top.glory.modules.system.entity.SysUser;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;


@Aspect
@Component
@Slf4j
public class HandleLogAspect {

    @Resource
    private HandleLogService handleLogService;

    // 定义切点Pointcut
    @Pointcut("execution(public * top.glory.modules..*.*Controller.*(..)) && (@annotation(top.glory.common.annotation.HandleLog))")
    public void excudeService() {
    }

    /**
     * 在方法执行之前和之后执行（计算执行时间）
     */
    @Around("excudeService()")
    public Object interceptor(ProceedingJoinPoint point) throws Throwable {
        // Spring计时器StopWatch
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 1.获取请求路径
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
        String url = httpServletRequest.getRequestURI();


        // 接收到请求，记录请求内容

        // 记录下请求内容
        String urlPath = httpServletRequest.getRequestURL().toString();
        String methodType = httpServletRequest.getMethod();
        String postMethod = point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName();
        String postData = Arrays.toString(point.getArgs());
//        log.info("URL : " + urlPath);
//        log.info("HTTP_METHOD : " + methodType);
//        log.info("IP : " + httpServletRequest.getRemoteAddr());
//        log.info("CLASS_METHOD : " + postMethod);
//        log.info("ARGS : " + postData);

        // 3.获取方法相关信息
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        HandleLog handleLog = method.getAnnotation(HandleLog.class);

        // 4.获取响应数据
        Object result = point.proceed();
        stopWatch.stop();

        // 5.存库
        SysHandleLog sysHandleLog = new SysHandleLog();
        SysUser currentUser = UserUtils.getCurrentUser();

        sysHandleLog.setLoginName(currentUser.getLoginName());
        sysHandleLog.setUsername(currentUser.getUsername());
        sysHandleLog.setUrlPath(urlPath);
        sysHandleLog.setPostType(methodType);
        sysHandleLog.setPostMethod(postMethod);
        sysHandleLog.setPostData(postData);
        sysHandleLog.setPostData(postData);
        sysHandleLog.setUseTime(stopWatch.getTime() + "");
        sysHandleLog.setReturnData(GsonUtils.objectToJsonStr(result));
        //设置日志文本信息
        sysHandleLog.setPostDesc(handleLog.value());
        handleLogService.save(sysHandleLog);

        return result;
    }

//    @Before("excudeService()")
//    public void doBefore(JoinPoint joinPoint) throws Throwable {
//
//    }
//
//    @AfterReturning(returning = "ret", pointcut = "excudeService()")
//    public void doAfterReturning(Object ret) throws Throwable {
//    }

}
