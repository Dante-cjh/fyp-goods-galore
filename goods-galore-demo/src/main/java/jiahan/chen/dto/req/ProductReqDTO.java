package jiahan.chen.dto.req;

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
public class ProductReqDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("产品名称")
    private String name;

    @ApiModelProperty("产品类型")
    private Integer categoryId;

    @ApiModelProperty("供应商id")
    private Integer supplierId;

    /**
     * 分页
     */
    private Integer pageNo=1;
}
