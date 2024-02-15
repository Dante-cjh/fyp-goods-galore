package jiahan.chen.controller;

import io.swagger.annotations.ApiOperation;
import jiahan.chen.base.BaseApiController;
import jiahan.chen.base.BaseResponse;
import jiahan.chen.constant.GoodsConstants;
import jiahan.chen.core.cache.LocalCache;
import jiahan.chen.entity.Account;
import jiahan.chen.dto.resp.AccountRespDTO;
import jiahan.chen.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        if(CollectionUtils.isEmpty(accountRespDTOList)){
            log.error("accountRespDTOList is empty");
            return setResultError("accountRespDTOList is empty");
        }
        List<Account> allAccount = accountService.getAllAccount();
        return setResultSuccessData(allAccount);
    }
}
