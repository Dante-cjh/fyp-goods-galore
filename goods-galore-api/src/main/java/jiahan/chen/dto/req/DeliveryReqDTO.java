package jiahan.chen.dto.req;

import lombok.Data;

@Data
public class DeliveryReqDTO {
    private String estimatedArrival; // ISO 8601格式的字符串，如"2023-01-30T15:20:30"
}
