package com.ningning0111.aop;

import cn.hutool.core.util.StrUtil;
import com.ningning0111.annotation.AuthCheck;
import com.ningning0111.config.TestCaseSystemConfig;
import com.ningning0111.exception.BusinessException;
import com.ningning0111.model.enums.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * @Project: com.ningning0111.aop
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/4 10:48
 * @Description:
 */
@Component
@Aspect
@RequiredArgsConstructor
public class AuthAop {
    private final TestCaseSystemConfig config;

    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint,AuthCheck authCheck) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 获取请求头例的auth信息
        String auth = request.getHeader("auth");
        String secretKey = config.getSecretKey();
        // 如果设置了secretKey，并且请求头里的auth信息与设置的key不等 则无权限访问
        if(StrUtil.isNotBlank(secretKey) && !Objects.equals(auth, secretKey)){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 否则放行
        return joinPoint.proceed();
    }
}
