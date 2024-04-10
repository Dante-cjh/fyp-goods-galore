package jiahan.chen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 用户权限菜单
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-04-03
 */
@TableName("role_menu_relationship")
@ApiModel(value = "RoleMenuRelationship对象", description = "用户权限菜单")
@Data
public class RoleMenuRelationship implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("角色id")
    private Integer roleId;

    @ApiModelProperty("角色菜单id")
    private Integer menuId;
}
