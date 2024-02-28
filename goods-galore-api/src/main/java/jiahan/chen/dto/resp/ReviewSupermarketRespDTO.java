package jiahan.chen.dto.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Jiahan Chen
 * @ClassName ReviewSupermarketRespDTO
 */
@Data
public class ReviewSupermarketRespDTO {
    @ApiModelProperty("评分")
    private Integer rating;

    @ApiModelProperty("评论内容")
    private String comment;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("供应商名称")
    private String supplierName;
}
