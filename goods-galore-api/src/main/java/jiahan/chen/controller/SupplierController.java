package jiahan.chen.controller;

import io.swagger.annotations.ApiOperation;
import jiahan.chen.base.BaseApiController;
import jiahan.chen.base.BaseResponse;
import jiahan.chen.dto.req.SupplierApplyReqDTO;
import jiahan.chen.dto.resp.ProductRespDTO;
import jiahan.chen.entity.Supplier;
import jiahan.chen.service.ISupplierApplicationService;
import jiahan.chen.service.ISupplierService;
import jiahan.chen.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 申请成为供应商
     */
    @PostMapping("/apply")
    @ApiOperation(value = "apply to be a supplier", notes = "apply to be a supplier")
    public BaseResponse apply(@RequestHeader String token, @RequestBody SupplierApplyReqDTO supplierApplyReqDTO) {
        // 验证参数
        if(supplierApplyReqDTO == null){
            log.error("[supplierApplyReqDTO is null]");
            return setResultError("[supplierApplyReqDTO is null]");
        }

        String name = supplierApplyReqDTO.getName();
        if(name.isEmpty()){
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
        if(supplier == null){
            log.error("[You are not a supplier]");
            return setResultError("[You are not a supplier]");
        }
        Integer supplierId = supplier.getSupplierId();
        List<ProductRespDTO> productRespDTOList = supplierService.getAllProducts(supplierId);

        return setResultSuccessData(productRespDTOList);
    }

}
