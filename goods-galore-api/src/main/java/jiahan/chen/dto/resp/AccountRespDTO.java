package jiahan.chen.dto.resp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Jiahan Chen
 * @ClassName AccountRespDTO
 */
@Data
public class AccountRespDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("角色(1 超市；2 供应商；3 管理员 )")
    private Integer role;

    @ApiModelProperty("显示名 （昵称）")
    private String showName;

    @ApiModelProperty("注册时间")
    private LocalDateTime createTime;

    @ApiModelProperty("是否可用 1正常  2冻结")
    private Boolean isAvalible;

    @ApiModelProperty("用户头像")
    private String picImg;
}
