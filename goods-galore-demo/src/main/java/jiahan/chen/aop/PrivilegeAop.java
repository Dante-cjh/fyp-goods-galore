package jiahan.chen.aop;

import jiahan.chen.base.BaseApiController;
import jiahan.chen.core.exception.UnauthorizedException;
import jiahan.chen.entity.Account;
import jiahan.chen.service.IAccountService;
import jiahan.chen.utils.TokenUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jiahan Chen
 * @ClassName PrivilegeAop
 */
@Aspect
@Component
public class PrivilegeAop extends BaseApiController {
    @Autowired
    private IAccountService accountService; // 用于获取account信息的服务

    @Before(value = "execution(public * jiahan.chen.controller.SuperAdminController.*(..))")
    public void checkSuperAdminRole(JoinPoint joinPoint) throws Throwable {
        // 获取请求的Token
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        if (token == null) {
            // 如果token为空，则抛出一个异常
            throw new UnauthorizedException("Token is empty.");
        }
        // 通过Token获取account对象
        Integer accountId = TokenUtils.getUserIdByToken(token);
        Account account = accountService.getByAccountId(accountId);
        if (account == null || account.getRole() != 3) {
            // 如果account为空，或者role不是3，则抛出一个异常
            throw new UnauthorizedException("You do not have permission to perform this action.");
        }
    }
}
