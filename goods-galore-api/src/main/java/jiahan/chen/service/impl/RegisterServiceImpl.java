package jiahan.chen.service.impl;

import jiahan.chen.constant.GoodsConstants;
import jiahan.chen.dto.req.AccountReqDTO;
import jiahan.chen.entity.Account;
import jiahan.chen.mapper.AccountMapper;
import jiahan.chen.service.IRegisterService;
import jiahan.chen.utils.MD5Utils;
import jiahan.chen.utils.SaltUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * @author Jiahan Chen
 * @ClassName RegisterServiceImpl
 */
@Service
@Slf4j
public class RegisterServiceImpl implements IRegisterService {

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    @Transactional
    public boolean register(AccountReqDTO accountReqDTO) {
        // 1.验证码参数
        String userName = accountReqDTO.getUsername();
        if (StringUtils.isEmpty(userName)) {
            log.error("[userName is null]");
            return Boolean.FALSE;
        }

        // 2.根据名称从db查找到用户信息
        Account dbAccount = accountService.getAccountByUsername(userName);
        if (dbAccount != null) {
            log.error("[该用户已存在 无需重复注册, dbAccount:{}]", accountReqDTO);
            return Boolean.FALSE;
        }
        String password = accountReqDTO.getPassword();
        if (StringUtils.isEmpty(password)) {
            log.error("[password is null]");
            return Boolean.FALSE;
        }

        // 3. 生成盐值，对密码进行加密
        String salt = SaltUtils.getSalt();
        String newPwd = MD5Utils.md5(password + salt);

        dbAccount = new Account();
        dbAccount.setAccountSalt(salt);
        dbAccount.setPassword(newPwd);
        dbAccount.setUsername(userName);
        dbAccount.setCreateTime(LocalDateTime.now());

        // 4. 注册（添加数据）
        return accountMapper.insert(dbAccount) > GoodsConstants.DB_INSERT_RESULT_BIGZERO;

    }

}
