package jiahan.chen.controller;

import io.swagger.annotations.ApiOperation;
import jiahan.chen.base.BaseApiController;
import jiahan.chen.base.BaseResponse;
import jiahan.chen.dto.req.OrderCreationRequest;
import jiahan.chen.dto.req.OrderReqDTO;
import jiahan.chen.dto.req.SupermarketApplyReqDTO;
import jiahan.chen.dto.req.SupplierApplyReqDTO;
import jiahan.chen.entity.Supermarket;
import jiahan.chen.service.*;
import jiahan.chen.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


}
