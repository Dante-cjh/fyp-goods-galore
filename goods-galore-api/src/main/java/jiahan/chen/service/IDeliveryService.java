package jiahan.chen.service;

import jiahan.chen.entity.Delivery;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-05
 */
public interface IDeliveryService extends IService<Delivery> {
    Delivery getDeliveryByOrderId(Integer orderId);
}
