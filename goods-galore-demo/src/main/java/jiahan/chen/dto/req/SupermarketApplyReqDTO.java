package jiahan.chen.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Jiahan Chen
 * @ClassName SupermarketApplyReqDTO
 */
@Data
public class SupermarketApplyReqDTO {

    @ApiModelProperty("超市名称")
    private String name;

    @ApiModelProperty("位置")
    private String address;

    @ApiModelProperty("联系电话")
    private String phoneNumber;

    @ApiModelProperty("联系邮箱")
    private String email;

    @ApiModelProperty("超市描述")
    private String description;

    @ApiModelProperty("证明文件(MultipartFile类型)")
    private MultipartFile documentaryProof;

}
