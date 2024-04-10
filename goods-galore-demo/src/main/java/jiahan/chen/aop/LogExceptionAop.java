package jiahan.chen.aop;

import jiahan.chen.base.BaseApiController;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jiahan Chen
 * @ClassName LogException
 */
@Aspect
@Component
@Slf4j
public class LogExceptionAop extends BaseApiController {
    /**
     * aop环绕通知
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around(value = "execution(public * jiahan.chen.controller..*.*(..))")
    public Object currentLimit(ProceedingJoinPoint pjp) throws Throwable {

        try {
            // 目标方法
            return pjp.proceed();
        } catch (Exception e) {
            log.error("e:{}", e);
            return setResultError(e.getMessage());
        }
    }
}
