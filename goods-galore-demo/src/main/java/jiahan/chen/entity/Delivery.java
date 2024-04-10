package jiahan.chen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2024-02-23
 */
@ApiModel(value = "Delivery对象", description = "")
@Data
public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Delivery_ID", type = IdType.AUTO)
    private Integer deliveryId;

    @TableField("ShippedDate")
    private LocalDateTime shippedDate;

    @TableField("EstimatedArrival")
    private LocalDateTime estimatedArrival;

    @TableField("ActualArrival")
    private LocalDateTime actualArrival;

    private Integer orderId;
}
