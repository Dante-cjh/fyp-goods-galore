package jiahan.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jiahan.chen.entity.Account;
import jiahan.chen.mapper.AccountMapper;
import jiahan.chen.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-05
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<Account> getAllAccount() {
        QueryWrapper<Account> objectQueryWrapper = new QueryWrapper<>();
        List<Account> accountList = accountMapper.selectList(objectQueryWrapper);
        return accountList;
    }

    @Override
    public Account getAccountByUsername(String username) {
//        QueryWrapper<Account> accountQueryWrapper = new QueryWrapper<>();
//        accountQueryWrapper.eq("username", username);

        LambdaQueryWrapper<Account> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Account::getUsername, username);
        Account account = accountMapper.selectOne(lambdaQueryWrapper);
        return account;
    }

    @Override
    public Account getByAccountId(Integer accountId) {
        return accountMapper.selectById(accountId);
    }
}
