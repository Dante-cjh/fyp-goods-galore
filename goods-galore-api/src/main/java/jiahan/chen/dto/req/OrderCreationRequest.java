package jiahan.chen.dto.req;

import lombok.Data;

import java.util.List;

@Data
public class OrderCreationRequest {
    private OrderReqDTO orderReqDTO;
    private List<OrderProductReqDTO> orderProducts;
}
