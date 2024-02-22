package jiahan.chen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-18
 */
@Data
@ApiModel(value = "Product对象", description = "")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Product_ID", type = IdType.AUTO)
    private Integer productId;

    @ApiModelProperty("产品名称")
    private String name;

    @ApiModelProperty("产品描述")
    private String description;

    @ApiModelProperty("单价")
    private BigDecimal price;

    @ApiModelProperty("计量单位")
    private String unit;

    @ApiModelProperty("商品图")
    private String productPic;

    @ApiModelProperty("产品类型")
    private Integer categoryId;

    @ApiModelProperty("供应商id")
    private Integer supplierId;
}
