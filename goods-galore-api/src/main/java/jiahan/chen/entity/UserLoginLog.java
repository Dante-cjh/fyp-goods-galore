package jiahan.chen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-16
 */
@TableName("user_login_log")
@ApiModel(value = "UserLoginLog对象", description = "")
@Data
public class UserLoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String loginIp;

    private Date loginTime;

    private String loginToken;

    // 安卓 ios
    @ApiModelProperty("渠道")
    private String channel;

    @ApiModelProperty("设备信息")
    private String equipment;

    private Integer isDelete;

    /**
     * 无参构造方法
     */
    public UserLoginLog() {

    }

    public UserLoginLog(Integer userId, String loginIp, String loginToken, String equipment) {
        this.userId = userId;
        this.loginIp = loginIp;
        this.loginTime = new Date();
        this.loginToken = loginToken;
        this.equipment = equipment;
    }
}
