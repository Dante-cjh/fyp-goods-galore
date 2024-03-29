package jiahan.chen.service;

import jiahan.chen.dto.req.SupermarketApplyReqDTO;
import jiahan.chen.entity.SupermarketApplication;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 申请成为超市 服务类
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-17
 */
public interface ISupermarketApplicationService extends IService<SupermarketApplication> {
    boolean submitApplication(Integer userId, SupermarketApplyReqDTO supermarketApplyReqDTO);

    boolean reviewApplication(Integer applicationId, Integer status);

    SupermarketApplication getByApplicationId(Integer applicationId);
}
