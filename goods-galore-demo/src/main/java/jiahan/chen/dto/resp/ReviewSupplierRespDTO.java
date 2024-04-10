package jiahan.chen.dto.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Jiahan Chen
 * @ClassName ReviewRespDTO
 */
@Data
public class ReviewSupplierRespDTO {

    @ApiModelProperty("评分")
    private Integer rating;

    @ApiModelProperty("评论内容")
    private String comment;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("超市名称")
    private String supermarketName;
}
