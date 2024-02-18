package jiahan.chen.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN) // 403 Forbidden
    public ResponseEntity<String> handleUnauthorizedException(UnauthorizedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }
}