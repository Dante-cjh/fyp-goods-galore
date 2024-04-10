package jiahan.chen.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum OrderStatus {
    INITIAL(0, "Initial"),
    CONFIRMED(1, "Confirmed"),
    PROCESSING(2, "Processing"),
    DELIVERED(3, "Delivered"),
    CANCELLED(4, "Cancelled"),
    PENDING_PAYMENT(5, "Pending Payment"),
    COMPLETED(6, "Completed");

    @EnumValue // 标记数据库存储的值
    private final Integer code;
    private final String description;

    OrderStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    // Getter方法
    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
