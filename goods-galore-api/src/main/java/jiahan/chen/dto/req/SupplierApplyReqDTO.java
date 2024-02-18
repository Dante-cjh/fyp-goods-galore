package jiahan.chen.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Jiahan Chen
 * @ClassName SupermarketApplyReqDTO
 */
@Data
public class SupplierApplyReqDTO {

    private String name;

    private String address;

    @ApiModelProperty("联系电话")
    private String phoneNumber;

    @ApiModelProperty("联系邮箱")
    private String email;

    @ApiModelProperty("供应商描述")
    private String description;

    private MultipartFile documentaryProof;

}
