package jiahan.chen.controller;

import io.swagger.annotations.ApiOperation;
import jiahan.chen.base.BaseApiController;
import jiahan.chen.base.BaseResponse;
import jiahan.chen.constant.OrderStatus;
import jiahan.chen.dto.req.DeliveryReqDTO;
import jiahan.chen.dto.req.SupplierApplyReqDTO;
import jiahan.chen.dto.resp.ProductRespDTO;
import jiahan.chen.dto.resp.ReviewSupermarketRespDTO;
import jiahan.chen.dto.resp.ReviewSupplierRespDTO;
import jiahan.chen.entity.Supermarket;
import jiahan.chen.entity.Supplier;
import jiahan.chen.entity.TOrder;
import jiahan.chen.service.IOrderService;
import jiahan.chen.service.IReviewService;
import jiahan.chen.service.ISupplierApplicationService;
import jiahan.chen.service.ISupplierService;
import jiahan.chen.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

/**
 * @author Jiahan Chen
 * @ClassName SupplierController
 */
@RestController
@RequestMapping("/supplier")
@ApiOperation(value = "供应商", notes = "供应商")
@Slf4j
public class SupplierController extends BaseApiController {
    @Autowired
    private ISupplierApplicationService supplierApplicationService;

    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IReviewService reviewService;

    /**
     * 申请成为供应商
     */
    @PostMapping("/apply")
    @ApiOperation(value = "apply to be a supplier", notes = "apply to be a supplier")
    public BaseResponse apply(@RequestHeader String token, @RequestBody SupplierApplyReqDTO supplierApplyReqDTO) {
        // 验证参数
        if (supplierApplyReqDTO == null) {
            log.error("[supplierApplyReqDTO is null]");
            return setResultError("[supplierApplyReqDTO is null]");
        }

        String name = supplierApplyReqDTO.getName();
        if (name.isEmpty()) {
            log.error("[supermarket name is null]");
            return setResultError("[supermarket name is null]");
        }

        Integer userId = TokenUtils.getUserIdByToken(token);

        // 提交申请
        return supplierApplicationService.submitApplication(userId, supplierApplyReqDTO) ? setResultSuccess() : setResultError();
    }

    @GetMapping("/getAllProducts")
    @ApiOperation(value = "get all products", notes = "get all products")
    public BaseResponse getAllProducts(@RequestHeader String token) {
        // 获得当前用户
        Integer userId = TokenUtils.getUserIdByToken(token);
        Supplier supplier = supplierService.getSupplierByAccountId(userId);
        if (supplier == null) {
            log.error("[You are not a supplier]");
            return setResultError("[You are not a supplier]");
        }
        Integer supplierId = supplier.getSupplierId();
        List<ProductRespDTO> productRespDTOList = supplierService.getAllProducts(supplierId);

        return setResultSuccessData(productRespDTOList);
    }

    /**
     * 供应商确认订单
     * @return
     */
    @PostMapping("/orders/{orderId}/confirm")
    @ApiOperation(value = "confirm order", notes = "confirm order")
    public BaseResponse confirmOrder(@RequestHeader String token, @PathVariable Integer orderId) {
        // 获得当前用户
        Integer accountId = TokenUtils.getUserIdByToken(token);
        Supplier supplier = supplierService.getSupplierByAccountId(accountId);

        // 获取订单信息
        TOrder order = orderService.findOrderByOrderId(orderId);

        // 判断该供应商是否为订单的供应商
        if (!Objects.equals(supplier.getSupplierId(), order.getSupplierId())) {
            log.error("[You are not the supplier of this order]");
            return setResultError("[You are not the supplier of this order]");
        }

        // 判断订单的状态是否为Initial
        if (!order.getStatus().equals(OrderStatus.INITIAL)) {
            log.error("[The order status is not INITIAL]");
            return setResultError("[The order status is not INITIAL]");
        }

        return orderService.updateOrderStatus(orderId, OrderStatus.CONFIRMED) ? setResultSuccess() : setResultError("update order status failed");
    }

    /**
     * 供应商处理订单
     */
    @PostMapping("/orders/{orderId}/process")
    @ApiOperation(value = "process order", notes = "process order")
    public BaseResponse processOrder(@RequestHeader String token, @PathVariable Integer orderId) {
        // 获得当前用户
        Integer accountId = TokenUtils.getUserIdByToken(token);
        Supplier supplier = supplierService.getSupplierByAccountId(accountId);

        // 获取订单信息
        TOrder order = orderService.findOrderByOrderId(orderId);

        // 判断该供应商是否为订单的供应商
        if (!Objects.equals(supplier.getSupplierId(), order.getSupplierId())) {
            log.error("[You are not the supplier of this order]");
            return setResultError("[You are not the supplier of this order]");
        }

        // 判断订单的状态是否为CONFIRMED
        if (!order.getStatus().equals(OrderStatus.CONFIRMED)) {
            log.error("[The order status is not CONFIRMED]");
            return setResultError("[The order status is not CONFIRMED]");
        }

        return orderService.updateOrderStatus(orderId, OrderStatus.PROCESSING) ? setResultSuccess() : setResultError("update order status failed");
    }

    /**
     * 供应商取消订单
     */
    @PostMapping("/orders/{orderId}/cancel")
    @ApiOperation(value = "cancel order", notes = "cancel order")
    public BaseResponse cancelOrder(@RequestHeader String token, @PathVariable Integer orderId) {

        // 获得当前用户
        Integer accountId = TokenUtils.getUserIdByToken(token);
        Supplier supplier = supplierService.getSupplierByAccountId(accountId);

        // 获取订单信息
        TOrder order = orderService.findOrderByOrderId(orderId);

        // 判断该供应商是否为订单的供应商
        if (!Objects.equals(supplier.getSupplierId(), order.getSupplierId())) {
            log.error("[You are not the supplier of this order]");
            return setResultError("[You are not the supplier of this order]");
        }

        return orderService.updateOrderStatus(orderId, OrderStatus.CANCELLED) ? setResultSuccess() : setResultError("update order status failed");
    }

    /**
     * 供应商配送订单
     */
    @PostMapping("/orders/{orderId}/deliver")
    @ApiOperation(value = "deliver order", notes = "deliver order")
    public BaseResponse deliverOrder(@RequestHeader String token, @PathVariable Integer orderId, @RequestBody DeliveryReqDTO deliveryReqDTO) {

        // 获得当前用户
        Integer accountId = TokenUtils.getUserIdByToken(token);
        Supplier supplier = supplierService.getSupplierByAccountId(accountId);

        // 获取订单信息
        TOrder order = orderService.findOrderByOrderId(orderId);

        // 判断该供应商是否为订单的供应商
        if (!Objects.equals(supplier.getSupplierId(), order.getSupplierId())) {
            log.error("[You are not the supplier of this order]");
            return setResultError("[You are not the supplier of this order]");
        }

        // 解析estimatedArrival字符串为LocalDateTime
        LocalDateTime estimatedArrival = LocalDateTime.parse(deliveryReqDTO.getEstimatedArrival(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        return orderService.orderDelivery(orderId, estimatedArrival) ? setResultSuccess() : setResultError("deliver order failed");
    }

    /**
     * 供应商确实付款，完成订单
     */
    @PostMapping("/orders/{orderId}/complete")
    @ApiOperation(value = "complete order", notes = "complete order")
    public BaseResponse receiveOrder(@RequestHeader String token, @PathVariable Integer orderId) {

        // 获得当前用户
        Integer accountId = TokenUtils.getUserIdByToken(token);
        Supplier supplier = supplierService.getSupplierByAccountId(accountId);

        // 获取订单信息
        TOrder order = orderService.findOrderByOrderId(orderId);

        // 判断该供应商是否为订单的供应商
        if (!Objects.equals(supplier.getSupplierId(), order.getSupplierId())) {
            log.error("[You are not the supplier of this order]");
            return setResultError("[You are not the supplier of this order]");
        }

        // 判断订单的状态是否为PENDING_PAYMENT
        if (!order.getStatus().equals(OrderStatus.PENDING_PAYMENT)) {
            log.error("[The order status is not PENDING_PAYMENT]");
            return setResultError("[The order status is not PENDING_PAYMENT]");
        }

        return orderService.updateOrderStatus(orderId, OrderStatus.COMPLETED) ? setResultSuccess() : setResultError("update order status failed");
    }

    /**
     * 获得所有自己的评论
     */
    @GetMapping("/getReviews/{supplierId}")
    @ApiOperation(value = "Get all your own reviews", notes = "Get all your own reviews")
    public BaseResponse getReviews(@PathVariable Integer supplierId) {

        if (supplierId == null) {
            log.error("[supplierId is null]");
            return setResultError("[supplierId is null]");
        }
        List<ReviewSupplierRespDTO> reviews = reviewService.getReviewsBySupplierId(supplierId);
        return  setResultSuccessData(reviews);
    }
}
