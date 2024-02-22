package jiahan.chen.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Jiahan Chen
 * @ClassName OrderProductReqDTO
 */
@Data
public class OrderProductReqDTO {
    @ApiModelProperty("商品id")
    private Integer productId;

    @ApiModelProperty("产品数量")
    private Integer productNumber;
}
