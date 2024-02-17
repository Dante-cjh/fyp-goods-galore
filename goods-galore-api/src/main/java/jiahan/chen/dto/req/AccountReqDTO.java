package jiahan.chen.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Jiahan Chen
 * @ClassName AccountReqDTO
 */
@Data
public class AccountReqDTO {

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;
}
