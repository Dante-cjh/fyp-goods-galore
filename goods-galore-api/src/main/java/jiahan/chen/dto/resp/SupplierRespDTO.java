package jiahan.chen.dto.resp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Jiahan Chen
 * @ClassName SupplierRespDTO
 */
@Data
public class SupplierRespDTO {

    private String name;

    private String address;

    @ApiModelProperty("联系电话")
    private String phoneNumber;

    @ApiModelProperty("联系邮箱")
    private String email;

    @ApiModelProperty("描述")
    private String description;
}
