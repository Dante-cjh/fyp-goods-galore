package jiahan.chen.dto.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Jiahan Chen
 * @ClassName SupermarketRespDTO
 */
@Data
public class SupermarketRespDTO {
    @ApiModelProperty("超市名称")
    private String name;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("联系电话")
    private String phoneNumber;

    @ApiModelProperty("联系邮箱")
    private String email;

    @ApiModelProperty("超市描述")
    private String description;
}
