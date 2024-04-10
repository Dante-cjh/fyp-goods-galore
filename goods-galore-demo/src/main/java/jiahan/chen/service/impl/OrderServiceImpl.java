package jiahan.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jiahan.chen.constant.GoodsConstants;
import jiahan.chen.constant.OrderStatus;
import jiahan.chen.dto.req.DeliveryReqDTO;
import jiahan.chen.dto.req.OrderProductReqDTO;
import jiahan.chen.dto.req.OrderReqDTO;
import jiahan.chen.dto.resp.OrderRespDTO;
import jiahan.chen.entity.Delivery;
import jiahan.chen.entity.TOrder;
import jiahan.chen.entity.OrderProduct;
import jiahan.chen.entity.Product;
import jiahan.chen.mapper.DeliveryMapper;
import jiahan.chen.mapper.OrderMapper;
import jiahan.chen.mapper.OrderProductMapper;
import jiahan.chen.mapper.ProductMapper;
import jiahan.chen.service.IDeliveryService;
import jiahan.chen.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jiahan.chen.service.ISupplierService;
import jiahan.chen.utils.OrderConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-05
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, TOrder> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private DeliveryMapper deliveryMapper;

    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private IDeliveryService deliveryService;

    @Override
    @Transactional
    public boolean createOrder(OrderReqDTO orderReqDTO, List<OrderProductReqDTO> orderProductsReqDTO, Integer supermarketId) {
        // 检查参数
        Integer supplierId = orderReqDTO.getSupplierId();
        if (supplierId == null) {
            log.error("supplierId is null");
            return false;
        }
        if (supplierService.getSupplierBySupplierId(supplierId) == null) {
            log.error("supplier not found");
            return false;
        }
        if (orderProductsReqDTO == null || orderProductsReqDTO.size() == 0) {
            log.error("orderProductsReqDTO is empty");
            return false;
        }

        // 生成订单
        TOrder tOrder = new TOrder();
        tOrder.setSupplierId(supplierId);
        tOrder.setSupermarketId(supermarketId);
        tOrder.setRemark(orderReqDTO.getRemark());
        tOrder.setStatus(OrderStatus.INITIAL);

        // 插入订单记录
        if(orderMapper.insert(tOrder) <= GoodsConstants.DB_INSERT_RESULT_BIGZERO){
            log.error("insert order failed");
            return false;
        };

        // 使用回填的Order ID处理OrderProduct记录(插入到数据库后会自动设置一个orderId并且填充到对象中)
        ArrayList<OrderProduct> orderProducts = new ArrayList<>();
        // 遍历订单商品
        for (OrderProductReqDTO orderProductReqDTO : orderProductsReqDTO) {
            OrderProduct orderProduct = new OrderProduct();
            // 设置OrderProduct字段，包括从order获取的ID
            orderProduct.setOrderorderId(tOrder.getOrderId());

            // 获取产品信息
            Integer productId = orderProductReqDTO.getProductId();
            if (productId == null) {
                log.error("productId is null");
                return false;
            }
            Product product = productMapper.selectById(productId);
            orderProduct.setProductName(product.getName());
            orderProduct.setProductproductId(productId);
            orderProduct.setProductNumber(orderProductReqDTO.getProductNumber());
            // 插入OrderProduct记录
            if(orderProductMapper.insert(orderProduct) <= GoodsConstants.DB_INSERT_RESULT_BIGZERO){
                log.error("insert orderProduct failed");
                return false;
            }
            orderProducts.add(orderProduct);
        }

        // 计算总价格
        BigDecimal totalPrice = calculateTotalPrice(orderProducts);
        tOrder.setTotalPrice(totalPrice);

        return orderMapper.updateById(tOrder) > GoodsConstants.DB_INSERT_RESULT_BIGZERO;
    }

    @Override
    @Transactional
    public boolean updateOrderStatus(Integer orderId, OrderStatus status) {
        TOrder TOrder = findOrderByOrderId(orderId);
        if (TOrder == null) {
            log.error("order not found");
            return false;
        }
        TOrder.setStatus(status);
        if (status == OrderStatus.DELIVERED) {
            Delivery delivery = deliveryService.getDeliveryByOrderId(orderId);
            if (delivery == null) {
                log.error("delivery not found");
                return false;
            }

            delivery.setActualArrival(LocalDateTime.now());
            deliveryMapper.updateById(delivery);
        }
        return orderMapper.updateById(TOrder) > GoodsConstants.DB_UPDATE_RESULT_BIGZERO;
    }

    private BigDecimal calculateTotalPrice(List<OrderProduct> orderProducts) {
        BigDecimal totalPrice = BigDecimal.ZERO; // 初始化为0
        for (OrderProduct orderProduct : orderProducts) {
            Product product = productMapper.selectById(orderProduct.getProductproductId());
            BigDecimal quantity = BigDecimal.valueOf(orderProduct.getProductNumber());
            BigDecimal productTotal = product.getPrice().multiply(quantity); // 使用multiply方法计算
            totalPrice = totalPrice.add(productTotal); // 累加到总价中
        }
        return totalPrice;
    }


    @Override
    public TOrder findOrderByOrderId(Integer orderId) {
        LambdaQueryWrapper<TOrder> orderLambdaQueryWrapper = new LambdaQueryWrapper<>();
        orderLambdaQueryWrapper.eq(TOrder::getOrderId, orderId);
        return orderMapper.selectOne(orderLambdaQueryWrapper);
    }

    @Override
    @Transactional
    public boolean orderDelivery(Integer orderId, LocalDateTime estimatedArrival) {
        // 1. 更新订单状态为DELIVERED
        TOrder order = orderMapper.selectById(orderId);
        if (order == null) {
            return false; // 订单不存在
        }
        order.setStatus(OrderStatus.DELIVERED);
        orderMapper.updateById(order);

        // 2. 创建delivery记录
        Delivery delivery = new Delivery();
        delivery.setOrderId(orderId);
        delivery.setShippedDate(LocalDateTime.now());
        delivery.setEstimatedArrival(estimatedArrival);

        return deliveryMapper.insert(delivery) > GoodsConstants.DB_INSERT_RESULT_BIGZERO;
    }

    @Override
    public OrderRespDTO getOrderDetails(Integer orderId) {
        TOrder TOrder = orderMapper.selectById(orderId);
        List<OrderProduct> orderProducts = orderProductMapper.selectList(new QueryWrapper<OrderProduct>().eq("orderorderId", orderId));
        return OrderConvertUtil.convertToOrderRespDTO(TOrder, orderProducts);
    }
}
