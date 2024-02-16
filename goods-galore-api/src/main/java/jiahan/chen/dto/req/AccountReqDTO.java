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

    @ApiModelProperty("角色(1 超市；2 供应商；3 管理员 )")
    private Integer role;
}
