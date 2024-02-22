package jiahan.chen.dto.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Jiahan Chen
 * @ClassName OrderProductRespDTO
 */
@Data
public class OrderProductRespDTO {
    @ApiModelProperty("外键商品id")
    private Integer productproductId;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("产品数量")
    private Integer productNumber;
}
