package jiahan.chen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-17
 */
@Data
@ApiModel(value = "Supermarket对象", description = "")
public class Supermarket implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "supermarket_ID", type = IdType.AUTO)
    private Integer supermarketId;

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

    @ApiModelProperty("账户id")
    private Integer accountId;
}
