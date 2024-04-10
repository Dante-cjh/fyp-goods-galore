package jiahan.chen.service;

import jiahan.chen.entity.UserLoginLog;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-16
 */
public interface IUserLoginLogService extends IService<UserLoginLog> {
    /**
     * 记录用户登录日志 异步
     * @param userLoginLog
     */
     void addUserLoginLog(UserLoginLog userLoginLog);
}
