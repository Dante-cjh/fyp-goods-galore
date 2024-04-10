package jiahan.chen.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Jiahan Chen
 * @ClassName ReviewReqDTO
 */
@Data
public class ReviewReqDTO {
    @ApiModelProperty("评分")
    private Integer rating;

    @ApiModelProperty("评论内容")
    private String comment;

    @ApiModelProperty("供应商ID")
    private Integer supplierId;
}
