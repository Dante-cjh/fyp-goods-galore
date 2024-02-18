package jiahan.chen.core.exception;

public class UnauthorizedException extends RuntimeException {

    private final String errorCode; // 错误代码

    // 基本构造函数，只包含错误消息
    public UnauthorizedException(String message) {
        super(message);
        this.errorCode = "UNAUTHORIZED"; // 默认错误代码
    }

    // 包含错误代码和错误消息的构造函数
    public UnauthorizedException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    // 获取错误代码的方法
    public String getErrorCode() {
        return errorCode;
    }

    // 可以根据需要添加更多的功能
}
