package jiahan.chen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-05
 */
@ApiModel(value = "Order对象", description = "")
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "order_ID", type = IdType.AUTO)
    private Integer orderId;

    @ApiModelProperty("供应商id")
    private Integer supplierId;

    private Integer supermarketId;

    @ApiModelProperty("产品id")
    private Integer productId;

    @ApiModelProperty("产品数量")
    private Integer productNumber;

    @ApiModelProperty("总共价格")
    private Double totalPrice;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("订单创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("订单状态")
    private Integer status;

}
