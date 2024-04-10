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
 * 申请成为供应商
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-17
 */
@TableName("supplier_application")
@ApiModel(value = "SupplierApplication对象", description = "申请成为供应商")
@Data
public class SupplierApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("申请id")
    @TableId(value = "supplier_apply_id", type = IdType.AUTO)
    private Integer supplierApplyId;

    @ApiModelProperty("申请者id")
    private Integer accountId;

    @ApiModelProperty("申请状态(1. pending; 2. Approved; 3. Rejected)")
    private Integer applicationStatus;

    @ApiModelProperty("提交日期")
    private LocalDateTime submissionDate;

    private LocalDateTime auditDate;

    @ApiModelProperty("供应商名称")
    private String name;

    @ApiModelProperty("位置")
    private String address;

    @ApiModelProperty("联系电话")
    private String phoneNumber;

    @ApiModelProperty("联系邮箱")
    private String email;

    @ApiModelProperty("供应商描述")
    private String description;

    @ApiModelProperty("证明文件")
    private String documentaryProof;
}
