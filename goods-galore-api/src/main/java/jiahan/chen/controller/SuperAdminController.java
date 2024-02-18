package jiahan.chen.controller;

import io.swagger.annotations.ApiOperation;
import jiahan.chen.base.BaseApiController;
import jiahan.chen.base.BaseResponse;
import jiahan.chen.service.ISupermarketApplicationService;
import jiahan.chen.service.ISupplierApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jiahan Chen
 * @ClassName SuperAdminController
 */
@RestController
@RequestMapping("/admin")
@ApiOperation(value = "超级管理员", notes = "超级管理员")
@Slf4j
public class SuperAdminController extends BaseApiController {
    @Autowired
    private ISupplierApplicationService supplierApplicationService;

    @Autowired
    private ISupermarketApplicationService supermarketApplicationService;

    @PostMapping("/review/supplier/{applicationId}")
    @ApiOperation(value = "admin review supplier application", notes = "admin review supplier application")
    public BaseResponse supplierReview(@PathVariable Integer applicationId, @RequestParam Integer status) {
        // 验证参数
        if(status == null){
            log.error("[status is null]");
            return setResultError("[status is null]");
        }

        return supplierApplicationService.reviewApplication(applicationId, status) ? setResultSuccess() : setResultError();
    }

    @PostMapping("/review/supermarket/{applicationId}")
    @ApiOperation(value = "admin review supermarket application", notes = "admin review supermarket application")
    public BaseResponse supermarketReview(@PathVariable Integer applicationId, @RequestParam Integer status) {
        // 验证参数
        if(status == null){
            log.error("[status is null]");
            return setResultError("[status is null]");
        }
        return supermarketApplicationService.reviewApplication(applicationId, status) ? setResultSuccess() : setResultError();
    }

}
