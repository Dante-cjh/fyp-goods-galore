package jiahan.chen.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局捕获异常
 */
@Slf4j
@ControllerAdvice(basePackages = "jiahan.chen.controller")
public class GlobalExceptionHandler {

    /**
     * 全局捕获异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String, Object> errorResult(Exception e) {
        log.error("<error{}>", e);
        Map<String, Object> errorResultMap = new HashMap<String, Object>();
        errorResultMap.put("code", "500");
        errorResultMap.put("msg", "系统出现错误!");
        return errorResultMap;
    }
}