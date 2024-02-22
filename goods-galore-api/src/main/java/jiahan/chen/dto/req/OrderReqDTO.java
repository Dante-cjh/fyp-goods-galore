package jiahan.chen.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Jiahan Chen
 * @ClassName OrderReqDTO
 */
@Data
public class OrderReqDTO {
    @ApiModelProperty("供应商id")
    private Integer supplierId;

    @ApiModelProperty("备注")
    private String remark;
}
