package jiahan.chen.dto.resp;

import io.swagger.annotations.ApiModelProperty;
import jiahan.chen.constant.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Jiahan Chen
 * @ClassName OrderRespDTO
 */
@Data
public class OrderRespDTO {

    @ApiModelProperty("供应商id")
    private Integer supplierId;

    @ApiModelProperty("超市id")
    private Integer supermarketId;

    @ApiModelProperty("商品明细")
    private List<OrderProductRespDTO> products;

    @ApiModelProperty("总共价格")
    private BigDecimal totalPrice;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("订单创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("订单状态(0.initial; 1.confirmed; 2.processing; 3.delivered; 4.cancelled; 5.pending payment; 6.completed)")
    private OrderStatus status;


}
