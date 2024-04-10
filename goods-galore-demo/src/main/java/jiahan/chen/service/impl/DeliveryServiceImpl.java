package jiahan.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jiahan.chen.entity.Delivery;
import jiahan.chen.mapper.DeliveryMapper;
import jiahan.chen.service.IDeliveryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-05
 */
@Service
public class DeliveryServiceImpl extends ServiceImpl<DeliveryMapper, Delivery> implements IDeliveryService {

    @Autowired
    private DeliveryMapper deliveryMapper;

    @Override
    public Delivery getDeliveryByOrderId(Integer orderId) {
        QueryWrapper<Delivery> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        return deliveryMapper.selectOne(queryWrapper);
    }
}
