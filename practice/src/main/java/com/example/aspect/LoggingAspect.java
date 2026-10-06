package com.example.aspect;

import com.example.annotation.Logging;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Around("@annotation(logging)")
    public Object logging(ProceedingJoinPoint proceedingJoinPoint, Logging logging) throws Throwable {
        long startTime = System.currentTimeMillis();

        String methodName = logging.value();
        if (methodName.isBlank()) {
            methodName = proceedingJoinPoint.getSignature().getName();
        }

        try {

            Object result = proceedingJoinPoint.proceed();

            log.info("Execution time of method {} is {}", methodName, System.currentTimeMillis() - startTime);

            return result;
        } catch (Exception ex) {
            log.error("Method {} status = FAILED in {} time", methodName, System.currentTimeMillis() - startTime, ex);
            throw ex;
        }
    }
}
