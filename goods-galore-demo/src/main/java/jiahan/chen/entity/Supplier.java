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
@ApiModel(value = "Supplier对象", description = "")
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "supplier_id", type = IdType.AUTO)
    private Integer supplierId;

    private String name;

    private String address;

    @ApiModelProperty("联系电话")
    private String phoneNumber;

    @ApiModelProperty("联系邮箱")
    private String email;

    @ApiModelProperty("描述")
    private String description;

    private Integer accountId;

}
