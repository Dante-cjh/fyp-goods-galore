package jiahan.chen.service.impl;

import jiahan.chen.entity.UserLoginLog;
import jiahan.chen.mapper.UserLoginLogMapper;
import jiahan.chen.service.IUserLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-16
 */
@Service
@Slf4j
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLog> implements IUserLoginLogService {
    @Autowired
    private UserLoginLogMapper userLoginLogMapper;
    @Override
    @Transactional
    @Async("newSaskExecutor")
    public void addUserLoginLog(UserLoginLog userLoginLog) {
        int insertResult = userLoginLogMapper.insert(userLoginLog);
        log.info("insertResult: {}", insertResult);
    }
}
