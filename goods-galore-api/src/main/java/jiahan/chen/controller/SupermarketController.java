package jiahan.chen.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import jiahan.chen.base.BaseApiController;
import jiahan.chen.base.BaseResponse;
import jiahan.chen.constant.OrderStatus;
import jiahan.chen.dto.req.OrderCreationRequest;
import jiahan.chen.dto.req.ReviewReqDTO;
import jiahan.chen.dto.req.SupermarketApplyReqDTO;
import jiahan.chen.dto.resp.ReviewSupermarketRespDTO;
import jiahan.chen.entity.Supermarket;
import jiahan.chen.entity.TOrder;
import jiahan.chen.service.*;
import jiahan.chen.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author Jiahan Chen
 * @ClassName SupermarketController
 */
@RestController
@RequestMapping("/supermarket")
@ApiOperation(value = "Supermarket API", notes = "Supermarket API")
@Slf4j
public class SupermarketController extends BaseApiController {
    @Autowired
    private ISupermarketApplicationService supermarketApplicationService;

    @Autowired
    private ISupermarketService supermarketService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IReviewService reviewService;

    /**
     * 申请成为超市
     */
    @PostMapping("/apply")
    @ApiOperation(value = "apply to be a supermarket", notes = "apply to be a supermarket")
    public BaseResponse apply(@RequestHeader String token, @RequestBody SupermarketApplyReqDTO supermarketApplyReqDTO) {
        // 验证参数
        if(supermarketApplyReqDTO == null){
            log.error("[supermarketApplyReqDTO is null]");
            return setResultError("[supermarketApplyReqDTO is null]");
        }

        String name = supermarketApplyReqDTO.getName();
        if(name.isEmpty()){
            log.error("[supermarket name is null]");
            return setResultError("[supermarket name is null]");
        }

        Integer userId = TokenUtils.getUserIdByToken(token);

        // 提交申请
        return supermarketApplicationService.submitApplication(userId, supermarketApplyReqDTO) ? setResultSuccess() : setResultError();
    }

    /**
     * 创建订单
     */
    @PostMapping("/createOrder")
    @ApiOperation(value = "create order", notes = "create order")
    public BaseResponse createOrder(@RequestHeader String token, @RequestBody OrderCreationRequest request) {
        // 验证参数
        if(request == null){
            log.error("[request is null]");
            return setResultError("[request is null]");
        }
        if (request.getOrderProducts() == null || request.getOrderProducts().size() == 0) {
            log.error("[orderProductsReqDTO is empty]");
            return setResultError("[orderProductsReqDTO is empty]");
        }
        if (request.getOrderReqDTO() == null) {
            log.error("[orderReqDTO is null]");
            return setResultError("[orderReqDTO is null]");
        }

        // 创建订单
        Integer accountId = TokenUtils.getUserIdByToken(token);
        Supermarket supermarket = supermarketService.getSupermarketByAccountId(accountId);
        // 调用业务逻辑层的方法创建订单
        boolean result = orderService.createOrder(request.getOrderReqDTO(), request.getOrderProducts(), supermarket.getSupermarketId());
        if (result) {
            return setResultSuccess();
        } else {
            return setResultError("create order failed");
        }
    }

    /**
     * 超市取消订单
     */
    @PostMapping("/orders/{orderId}/cancel")
    @ApiOperation(value = "cancel order", notes = "cancel order")
    public BaseResponse cancelOrder(@RequestHeader String token, @PathVariable Integer orderId) {

        // 获得当前用户
        Integer accountId = TokenUtils.getUserIdByToken(token);
        Supermarket supermarket = supermarketService.getSupermarketByAccountId(accountId);

        // 获取订单信息
        TOrder order = orderService.findOrderByOrderId(orderId);

        // 判断该供应商是否为订单的供应商
        if (!Objects.equals(supermarket.getSupermarketId(), order.getSupermarketId())) {
            log.error("[You are not the supermarket of this order]");
            return setResultError("[You are not the supermarket of this order]");
        }

        return orderService.updateOrderStatus(orderId, OrderStatus.CANCELLED) ? setResultSuccess() : setResultError("update order status failed");
    }

    /**
     * 超市接收到货
     */
    @PostMapping("/orders/{orderId}/receive")
    @ApiOperation(value = "receive order", notes = "receive order")
    public BaseResponse receiveOrder(@RequestHeader String token, @PathVariable Integer orderId) {

        // 获得当前用户
        Integer accountId = TokenUtils.getUserIdByToken(token);
        Supermarket supermarket = supermarketService.getSupermarketByAccountId(accountId);

        // 获取订单信息
        TOrder order = orderService.findOrderByOrderId(orderId);

        // 判断该供应商是否为订单的供应商
        if (!Objects.equals(supermarket.getSupermarketId(), order.getSupermarketId())) {
            log.error("[You are not the supermarket of this order]");
            return setResultError("[You are not the supermarket of this order]");
        }

        // 判断订单的状态是否为DELIVERED
        if (!order.getStatus().equals(OrderStatus.DELIVERED)) {
            log.error("[The order status is not DELIVERED]");
            return setResultError("[The order status is not DELIVERED]");
        }

        return orderService.updateOrderStatus(orderId, OrderStatus.PENDING_PAYMENT) ? setResultSuccess() : setResultError("update order status failed");
    }

    /**
     * 创建评论
     */
    @PostMapping("/createReview/{supermarketId}")
    @ApiOperation(value = "create review", notes = "create review")
    public BaseResponse createReview(@PathVariable Integer supermarketId, @RequestBody ReviewReqDTO reviewReqDTO) {

        // 验证参数
        if(reviewReqDTO == null){
            log.error("[reviewReqDTO is null]");
            return setResultError("[reviewReqDTO is null]");
        }

        if (supermarketId == null) {
            log.error("[supermarketId is null]");
            return setResultError("[supermarketId is null]");
        }
        return reviewService.addReview(reviewReqDTO, supermarketId) ? setResultSuccess() : setResultError("create review failed");
    }

    /**
     * 获得所有自己评论过的评论
     */
    @GetMapping("/getReviews/{supermarketId}")
    @ApiOperation(value = "Get all comments you've commented on", notes = "Get all comments you've commented on")
    public BaseResponse getReviews(@PathVariable Integer supermarketId) {

        if (supermarketId == null) {
            log.error("[supermarketId is null]");
            return setResultError("[supermarketId is null]");
        }

        List<ReviewSupermarketRespDTO> reviews = reviewService.getReviewsBySupermarketId(supermarketId);
        return  setResultSuccessData(reviews);
    }
}
