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
 * 后台资源表
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-04-03
 */
@TableName("t_resource")
@ApiModel(value = "TResource对象", description = "后台资源表")
@Data
public class TResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("资源名称")
    private String name;

    @ApiModelProperty("资源URL")
    private String url;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("资源分类ID")
    private Long categoryId;
}
