package jiahan.chen.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

/**
 * @author Jiahan Chen
 * @ClassName Product2ReqDTO
 */
@Data
public class Product2ReqDTO {
    @ApiModelProperty("产品名称")
    private String name;

    @ApiModelProperty("产品描述")
    private String description;

    @ApiModelProperty("单价")
    private BigDecimal price;

    @ApiModelProperty("计量单位")
    private String unit;

    @ApiModelProperty("商品图")
    private MultipartFile productPic;

    @ApiModelProperty("产品类型")
    private Integer categoryId;
}
