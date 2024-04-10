package jiahan.chen.controller;

import io.swagger.annotations.ApiModelProperty;
import jiahan.chen.base.BaseApiController;
import jiahan.chen.base.BaseResponse;
import jiahan.chen.dto.req.AccountReqDTO;
import jiahan.chen.service.IRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiahan Chen
 * @ClassName RegisterServiceController
 */
@RestController
@Slf4j
public class RegisterController extends BaseApiController {
    @Autowired
    private IRegisterService registerService;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    @ApiModelProperty(value = "用户注册", notes = "用户注册")
    public BaseResponse register(@RequestBody AccountReqDTO accountReqDTO) {
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
        // 调用注册的业务逻辑
        return registerService.register(accountReqDTO) ? setResultSuccess() : setResultError();
    }
}
