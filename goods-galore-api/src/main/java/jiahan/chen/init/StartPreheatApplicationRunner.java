package jiahan.chen.init;

import jiahan.chen.service.IStartPreheatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 项目启动成功预热
 *
 * @author 余胜军
 * @ClassName StartPreheating
 */
@Component
@Slf4j
public class StartPreheatApplicationRunner implements ApplicationRunner {
    @Autowired
    private IStartPreheatService iStartPreheatService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("[开始预热db中数据到缓存中..]");
        iStartPreheatService.initData();
    }
}
