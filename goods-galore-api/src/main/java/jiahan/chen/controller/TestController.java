package jiahan.chen.controller;

import jiahan.chen.base.BaseApiController;
import jiahan.chen.base.BaseResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 该接口为测试接口
 *
 * @author Jiahan Chen
 * @ClassName TestController
 */
@RestController
@Slf4j
public class TestController extends BaseApiController {
    /**
     * 测试代码
     *
     * @return
     */
    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/test01")
    public BaseResponse test01(String name, Integer age) {
        if (StringUtils.isEmpty(name)) {
            return setResultError("name is null");
        }
        if (age == null) {
            return setResultError("age is error");
        }
        // 返回成功
        return setResultSuccess();
    }

    /**
     * 全局捕获异常 将整个项目发送异常统一拦截处理
     * 返回统一状态 500 系统 减少代码冗余性
     * 底层原理： AOP技术
     *
     * @param age
     * @return
     */
    @GetMapping("/test02")
    public BaseResponse test02(Integer age) {
        Integer j = 1 / age;
        // 返回成功
        return setResultSuccess();
    }

    @GetMapping("/test04")
    @ApiOperation(value = "测试传递参数", notes = "测试传递参数")
    @ApiImplicitParam(paramType = "query", name = "name", value = "传递name", required = true)
    public BaseResponse test04(String name) {
        log.info("name:{}", name);
        return setResultSuccess();
    }
}
