package jiahan.chen.service;

import jiahan.chen.dto.req.SupplierApplyReqDTO;
import jiahan.chen.entity.SupplierApplication;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 申请成为供应商 服务类
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-17
 */
public interface ISupplierApplicationService extends IService<SupplierApplication> {
    boolean submitApplication(Integer userId, SupplierApplyReqDTO supplierApplyReqDTO);

    boolean reviewApplication(Integer applicationId, Integer status);

    SupplierApplication getByApplicationId(Integer applicationId);
}
