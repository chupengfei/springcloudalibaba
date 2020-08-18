package com.qingkong.consumer.aspect;

import com.qingkong.consumer.annotation.LogDesc;
import com.qingkong.consumer.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(com.qingkong.consumer.annotation.LogDesc)")
    public void logPointcut() {
    }

    @Before("logPointcut()")
    public void processor(JoinPoint pjp) throws Throwable {
        Method originMethod = resolveMethod(pjp);
        LogDesc logDesc = originMethod.getAnnotation(LogDesc.class);
        if (logDesc == null) {
            throw new IllegalStateException("Wrong state for LogDesc annotation");
        }
        String desc = logDesc.desc();
        ResultEnum resultEnum = logDesc.resultEnum();
        String value = logDesc.value();
        log.warn("desc = {}, resultEnum = {}, value = {}", desc, resultEnum.getCode(), value);

    }


    protected Method resolveMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Class<?> targetClass = joinPoint.getTarget().getClass();

        Method method = getDeclaredMethodFor(targetClass, signature.getName(),
                signature.getMethod().getParameterTypes());
        Method method1 = signature.getMethod();
        System.out.println(method==method1);
        if (method == null) {
            throw new IllegalStateException("Cannot resolve target method: " + signature.getMethod().getName());
        }
        return method;
    }

    private Method getDeclaredMethodFor(Class<?> clazz, String name, Class<?>... parameterTypes) {
        try {
            return clazz.getDeclaredMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null) {
                return getDeclaredMethodFor(superClass, name, parameterTypes);
            }
        }
        return null;
    }


}
