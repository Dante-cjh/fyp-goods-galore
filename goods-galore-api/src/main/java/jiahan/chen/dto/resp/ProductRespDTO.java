package jiahan.chen.dto.resp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-05
 */
@ApiModel(value = "Product对象", description = "")
@Data
public class ProductRespDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("产品名称")
    private String name;

    @ApiModelProperty("产品描述")
    private String description;

    @ApiModelProperty("单价")
    private Double price;

    @ApiModelProperty("计量单位")
    private String unit;

    @ApiModelProperty("产品类型")
    private Integer categoryId;

    @ApiModelProperty("供应商id")
    private Integer supplierId;

}
