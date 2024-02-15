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
 * @since 2024-02-05
 */
@ApiModel(value = "Account对象", description = "")
@Data
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Account_ID", type = IdType.AUTO)
    private Integer accountId;

    private String username;

    private String password;

    private Integer role;
}
