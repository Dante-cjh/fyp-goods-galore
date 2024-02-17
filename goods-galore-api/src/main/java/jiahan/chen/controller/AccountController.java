package jiahan.chen.controller;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiOperation;
import jiahan.chen.base.BaseApiController;
import jiahan.chen.base.BaseResponse;
import jiahan.chen.constant.GoodsConstants;
import jiahan.chen.core.cache.LocalCache;
import jiahan.chen.dto.req.ProfileReqDTO;
import jiahan.chen.entity.Account;
import jiahan.chen.dto.resp.AccountRespDTO;
import jiahan.chen.service.IAccountService;
import jiahan.chen.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiahan Chen
 * @ClassName AccountController
 */
@RestController
@RequestMapping("/account")
@ApiOperation(value = "Accounts API", notes = "Accounts API")
@Slf4j
public class AccountController extends BaseApiController {
    @Autowired
    private IAccountService accountService;

    @GetMapping("/index")
    @ApiOperation(value = "Check all accounts", notes = "Check all accounts")
    public BaseResponse index() {
        List<AccountRespDTO> accountRespDTOList = new ArrayList<>();
        accountRespDTOList = LocalCache.get(GoodsConstants.ALL_ACCOUNTLIST, accountRespDTOList);
        if (CollectionUtils.isEmpty(accountRespDTOList)) {
            log.error("accountRespDTOList is empty");
            return setResultError("accountRespDTOList is empty");
        }
        List<Account> allAccount = accountService.getAllAccount();
        return setResultSuccessData(allAccount);
    }

    /**
     * 传递用户令牌
     *
     * @return
     */
    @GetMapping("/getByToken")
    @ApiOperation(value = "Get Account By Token", notes = "Get Account By Token")
    public BaseResponse getByTokenUserInfo(@RequestHeader String token) {
        // 验证参数
        if (StringUtils.isEmpty(token)) {
            log.error("[token is null]");
            return setResultError("[token is null]");
        }
        // 根据token查询用户信息
        String redisValue = RedisUtils.getString(token);
        if (StringUtils.isEmpty(redisValue)) {
            log.error("[redisValue is null]");
            return setResultError("[token is null]");
        }
        Integer userId = Integer.valueOf(redisValue);
        Account account = accountService.getByAccountId(userId);
        // do 与 dto 互转 敏感的数据过滤掉
        AccountRespDTO accountRespDTO = BeanUtil.toBean(account, AccountRespDTO.class);
        return setResultSuccessData(accountRespDTO);
    }

    @PostMapping("/profile/update")
    public BaseResponse updateUserProfile(@RequestHeader String token, @RequestBody ProfileReqDTO profileReqDTO) {
        // 验证参数
        if (StringUtils.isEmpty(token)) {
            log.error("[token is null]");
            return setResultError("[token is null]");
        }
        // 根据token查询用户信息
        String redisValue = RedisUtils.getString(token);
        if (StringUtils.isEmpty(redisValue)) {
            log.error("[redisValue is null]");
            return setResultError("[token is null]");
        }
        Integer userId = Integer.valueOf(redisValue);

        return accountService.updateAccountProfile(userId, profileReqDTO) ? setResultSuccess() : setResultError();
    }

}
