package jiahan.chen.entity;

import com.baomidou.mybatisplus.annotation.TableName;

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
@TableName("supermarket_review")
@ApiModel(value = "SupermarketReview对象", description = "")
@Data
public class SupermarketReview implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer supermarketsupermarketId;

    private Integer reviewreviewId;

}
