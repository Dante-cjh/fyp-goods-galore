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
@ApiModel(value = "Category对象", description = "")
@Data
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "category_ID", type = IdType.AUTO)
    private Integer categoryId;

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("描述")
    private String description;
}
