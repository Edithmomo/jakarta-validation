package com.example.config;

import com.example.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangjiameng
 * @date 2020-08-20 16:29
 */
@Slf4j
@Aspect
@Component
public class ServerAspect {

    @Around("execution(public * com.example.controller.*.*(..))")
    public Object controllerLog(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        long startTime = System.currentTimeMillis();

        Object[] args = point.getArgs();
        Object ret = point.proceed(args);

        long endTime = System.currentTimeMillis();

        String methodReference = point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName();
        log.info("============request" + methodReference + "start=======");
        log.info("| url\t: " + request.getRequestURI());
        log.info("| client ip: " + request.getRemoteAddr());
        log.info("| method\t: " + request.getMethod());
        log.info("| parameter: " + JsonUtil.toJsonString(args));
        log.info("| result\t: " + JsonUtil.toJsonString(ret));
        log.info("| time\t：" + (endTime - startTime) + "ms");
        log.info("============request" + methodReference + "end=======");
        return ret;

    }
}
