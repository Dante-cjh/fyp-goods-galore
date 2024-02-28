package jiahan.chen.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2024-02-05
 */
@ApiModel(value = "Review对象", description = "")
@Data
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "review_ID", type = IdType.AUTO)
    private Integer reviewId;

    @ApiModelProperty("评分")
    private Integer rating;

    @ApiModelProperty("评论内容")
    private String comment;

    private Integer supermarketId;

    private Integer supplierId;

    private LocalDateTime createTime;
}
