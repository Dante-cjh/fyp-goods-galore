package jiahan.chen.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Jiahan Chen
 * @ClassName ProfileReqDTO
 */
@Data
public class ProfileReqDTO {
    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("显示名 （昵称）")
    private String showName;

    @ApiModelProperty("用户头像(MultipartFile类型)")
    private MultipartFile picImg;
}
