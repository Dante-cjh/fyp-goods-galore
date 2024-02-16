package jiahan.chen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-15
 */
@ApiModel(value = "Account对象", description = "")
@Data
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Account_ID", type = IdType.AUTO)
    private Integer accountId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("角色(1 超市；2 供应商；3 管理员 )")
    private Integer role;

    @ApiModelProperty("用户密码盐值")
    private String accountSalt;

    @ApiModelProperty("显示名 （昵称）")
    private String showName;

    @ApiModelProperty("注册时间")
    private LocalDateTime createTime;

    @ApiModelProperty("是否可用 1正常  2冻结")
    private Boolean isAvalible;

    @ApiModelProperty("用户头像")
    private String picImg;
}
