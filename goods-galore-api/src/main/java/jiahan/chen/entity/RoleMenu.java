package jiahan.chen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 后台菜单表
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-04-03
 */
@TableName("role_menu")
@ApiModel(value = "RoleMenu对象", description = "后台菜单表")
@Data
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("父级ID")
    private Long parentId;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("菜单名称")
    private String title;

    @ApiModelProperty("菜单级数")
    private Integer level;

    @ApiModelProperty("菜单排序")
    private Integer sort;

    @ApiModelProperty("前端名称")
    private String name;

    @ApiModelProperty("前端图标")
    private String icon;

    @ApiModelProperty("前端隐藏")
    private Integer hidden;
}
