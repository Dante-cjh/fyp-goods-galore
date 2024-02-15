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

}
