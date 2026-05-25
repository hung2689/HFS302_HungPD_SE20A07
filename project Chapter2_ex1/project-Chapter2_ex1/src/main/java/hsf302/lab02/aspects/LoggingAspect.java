package hsf302.lab02.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// TODO 5.1: @Aspect + @Component — đánh dấu class là Aspect và Spring bean
@Aspect
@Component
public class LoggingAspect {

    // TODO 5.2: @Before — intercept tất cả method trong hsf302.lab02.services.*.*(..)
    // In "[BEFORE] <tên method>"
    @Before("execution(* hsf302.lab02.services.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("[BEFORE] " + methodName);
    }

    // TODO 5.3: @AfterReturning — in "[AFTER] <tên method> returned: <result>"
    @AfterReturning(
            pointcut = "execution(* hsf302.lab02.services.*.*(..))",
            returning = "result"
    )
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("[AFTER] " + methodName + " returned: " + result);
    }

    // TODO 5.4: @Around — đo thời gian thực thi, in "[ELAPSED] <tên method> took <X>ms"
    @Around("execution(* hsf302.lab02.services.*.*(..))")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("[ELAPSED] " + methodName + " took " + elapsed + "ms");
        return result;
    }
}
