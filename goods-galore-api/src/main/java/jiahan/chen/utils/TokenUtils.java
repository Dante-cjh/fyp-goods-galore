package jiahan.chen.utils;

import org.springframework.util.StringUtils;

import java.util.UUID;

public class TokenUtils {

    public static String getToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static Integer getUserIdByToken(String token) {
        // 根据token查询用户信息
        String redisValue = RedisUtils.getString(token);
        return Integer.valueOf(redisValue);
    }
}