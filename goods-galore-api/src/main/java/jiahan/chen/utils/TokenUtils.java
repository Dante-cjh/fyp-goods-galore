package jiahan.chen.utils;

import java.util.UUID;

public class TokenUtils {

    public static String getToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}