package jiahan.chen.controller;

import jiahan.chen.base.BaseApiController;
import jiahan.chen.base.BaseResponse;
import jiahan.chen.dto.req.AccountReqDTO;
import jiahan.chen.entity.Account;
import jiahan.chen.service.IAccountService;
import jiahan.chen.utils.MD5Utils;
import jiahan.chen.utils.RedisUtils;
import jiahan.chen.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author Jiahan Chen
 * @ClassName LoginController
 */
@RestController
@Slf4j
public class LoginController extends BaseApiController {

    @Autowired
    private IAccountService accountService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public BaseResponse login(@RequestBody AccountReqDTO accountReqDTO) {
        // 验证码参数
        String userName = accountReqDTO.getUsername();
        if (StringUtils.isEmpty(userName)) {
            log.error("[userName is null]");
            return setResultError("[userName is null]");
        }
        String password = accountReqDTO.getPassword();
        if (StringUtils.isEmpty(password)) {
            log.error("[password is null]");
            return setResultError("[password is null]");
        }

        // 登录
        Account dbAccount = accountService.getAccountByUsername(userName);

        if (dbAccount == null) {
            log.error("[account not exist]");
            return setResultError("account not exist or password error");
        }

        // 查找到该用户信息
        String dbPwd = dbAccount.getPassword();
        String salt = dbAccount.getAccountSalt();
        String newPwd = MD5Utils.md5(password + salt);
        if (!newPwd.equals(dbPwd)) {
            log.error("[password error]");
            return setResultError("account not exist or password error");
        }

        // 生成对应的令牌token
        String userToken = TokenUtils.getToken();

        // 保存令牌
        RedisUtils.setString(userToken, dbAccount.getAccountId());
        
        // 返回
        HashMap<String, String> result = new HashMap<>();
        result.put("userToken", userToken);
        log.info("[user login success: {}]", userToken);
        return setResultSuccessData(result);
    }
}
