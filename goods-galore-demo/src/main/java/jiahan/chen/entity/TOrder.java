package jiahan.chen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jiahan.chen.constant.OrderStatus;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-22
 */
@TableName("t_order")
@ApiModel(value = "TOrder对象", description = "")
@Data
public class TOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "order_ID", type = IdType.AUTO)
    private Integer orderId;

    @ApiModelProperty("供应商id")
    private Integer supplierId;

    @ApiModelProperty("超市id")
    private Integer supermarketId;

    @ApiModelProperty("总共价格")
    private BigDecimal totalPrice;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("订单创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("订单状态(0.initial; 1.confirmed; 2.processing; 3.delivered; 4.cancelled; 5.pending payment; 6.completed)")
    private OrderStatus status;
}
