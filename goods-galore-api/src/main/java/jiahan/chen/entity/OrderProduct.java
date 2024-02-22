package jiahan.chen.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-22
 */
@TableName("order_product")
@ApiModel(value = "OrderProduct对象", description = "")
@Data
public class OrderProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("外键订单id")
    private Integer orderorderId;

    @ApiModelProperty("外键商品id")
    private Integer productproductId;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("产品数量")
    private Integer productNumber;
}
