package jiahan.chen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jiahan.chen.constant.OrderStatus;
import jiahan.chen.dto.req.DeliveryReqDTO;
import jiahan.chen.dto.req.OrderProductReqDTO;
import jiahan.chen.dto.req.OrderReqDTO;
import jiahan.chen.dto.resp.OrderRespDTO;
import jiahan.chen.entity.TOrder;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-05
 */
public interface IOrderService extends IService<TOrder> {

    /**
     * 生成订单
     */
    boolean createOrder(OrderReqDTO orderReqDTO, List<OrderProductReqDTO> orderProducts, Integer supermarketId);

    /**
     * 更新订单状态
     */
    boolean updateOrderStatus(Integer orderId, OrderStatus status);

    /**
     * 根据订单id查找订单
     */
    TOrder findOrderByOrderId(Integer orderId);

    /**
     * 订单配送
     */
    boolean orderDelivery(Integer orderId, LocalDateTime estimatedArrival);


    /**
     * 获取订单详情
     */
    OrderRespDTO getOrderDetails(Integer orderId);
}
