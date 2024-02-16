package jiahan.chen.service;

import jiahan.chen.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-05
 */
public interface IAccountService extends IService<Account> {
    List<Account> getAllAccount();

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    Account getAccountByUsername(String username);

    /**
     *  根据用户id查询用户信息
     */
    Account getByAccountId(Integer accountId);

}
