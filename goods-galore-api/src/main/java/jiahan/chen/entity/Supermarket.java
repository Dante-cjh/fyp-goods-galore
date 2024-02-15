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
@ApiModel(value = "Supermarket对象", description = "")
@Data
public class Supermarket implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "supermarket_ID", type = IdType.AUTO)
    private Integer supermarketId;

    private String name;

    private String address;

    private String contactInfo;

    private Integer accountId;

}
