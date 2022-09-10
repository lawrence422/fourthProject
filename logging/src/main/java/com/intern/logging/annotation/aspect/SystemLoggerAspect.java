package com.intern.logging.annotation.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intern.common.dao.SystemLog;
import com.intern.logging.annotation.SystemLogger;
import com.intern.logging.service.LoggingService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Aspect
@Component
public class SystemLoggerAspect {
    private static final Logger logger = LoggerFactory.getLogger(SystemLoggerAspect.class);
    SystemLog systemLog;
    @Autowired
    private LoggingService loggingService;
    @Autowired
    private ObjectMapper objectMapper;

    @Pointcut(value = "@annotation(com.intern.logging.annotation.SystemLogger)")
    public void logPointCut() {

    }

    @Pointcut("execution(* com.intern..*.*(..))&& !@annotation(com.intern.logging.annotation.NoLogging)")
    public void logExceptionCut() {

    }

    @Before("logPointCut()")
    public void beforeLog(JoinPoint joinPoint) {
        systemLog = new SystemLog();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        Map<String, String> map = null;
        if (request != null) {
            map = convertMap(request.getParameterMap());
        }
        try {
            systemLog.setSysParam(objectMapper.writeValueAsString(joinPoint.getArgs()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @AfterReturning(value = "logPointCut()", returning = "keys")
    public void saveLog(JoinPoint joinPoint, Object keys) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SystemLogger systemLogger = method.getAnnotation(SystemLogger.class);
        try {


            if (systemLogger != null) {
                String logModule = systemLogger.logModule();
                String logType = systemLogger.logType();
                String logDecs = systemLogger.logDecs();

                systemLog.setSysModule(logModule);
                systemLog.setSysType(logType);
                systemLog.setSysDesc(logDecs);
            }

            String className = joinPoint.getTarget().getClass().getName();
            String methodName = method.getName();
            StringBuffer stringBuffer = new StringBuffer(className);
            stringBuffer.append(".").append(methodName);

//            System.out.println(joinPoint.getArgs());
            systemLog.setSysDate(new Date());
            systemLog.setSysIp(request.getRemoteAddr());
            systemLog.setSysMethod(stringBuffer.toString());
//            systemLog.setSys_param(map != null ? map.toString() : joinPoint.getArgs().toString());
            systemLog.setSysResponse(keys.toString());
            loggingService.insertSystemLog(systemLog);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Map<String, String> convertMap(Map<String, String[]> paramMap) {
        Map<String, String> returnMap = new HashMap<>();
        for (String key : paramMap.keySet()) {
            returnMap.put(key, paramMap.get(key)[0]);
        }
        return returnMap;
    }

    @AfterThrowing(pointcut = "logExceptionCut()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
//        SystemLog systemLog = new SystemLog();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SystemLogger systemLogger = method.getAnnotation(SystemLogger.class);
        try {
            if (systemLogger != null) {
                String logModule = systemLogger.logModule();
                String logType = systemLogger.logType();
                String logDecs = systemLogger.logDecs();

                systemLog.setSysModule(logModule);
                systemLog.setSysType(logType);
                systemLog.setSysDesc(logDecs);
            }

            String className = joinPoint.getTarget().getClass().getName();
            String methodName = method.getName();
            StringBuffer stringBuffer = new StringBuffer(className);
            stringBuffer.append(".").append(methodName);
            Map<String, String> map = null;
            if (request != null) {
                map = convertMap(request.getParameterMap());
            }
//            System.out.println(joinPoint.getArgs());
            systemLog.setSysDate(new Date());
            systemLog.setSysIp(request.getRemoteAddr());
            systemLog.setSysMethod(stringBuffer.toString());
            systemLog.setSysParam(map != null ? map.toString() : joinPoint.getArgs().toString());
            systemLog.setSysResponse("Excetion Name: " + e.getClass().getName() + "; Exception Msg: " + e.getMessage() + "; StackTrace: " + e.getStackTrace());
            loggingService.insertExceptionLog(systemLog);

        } catch (Exception e2) {
            e.printStackTrace();
        }

    }
}

