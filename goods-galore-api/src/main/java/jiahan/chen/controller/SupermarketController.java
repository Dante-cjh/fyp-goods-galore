package jiahan.chen.controller;

import io.swagger.annotations.ApiOperation;
import jiahan.chen.base.BaseApiController;
import jiahan.chen.base.BaseResponse;
import jiahan.chen.dto.req.SupermarketApplyReqDTO;
import jiahan.chen.dto.req.SupplierApplyReqDTO;
import jiahan.chen.service.ISupermarketApplicationService;
import jiahan.chen.service.ISupermarketService;
import jiahan.chen.service.ISupplierApplicationService;
import jiahan.chen.service.ISupplierService;
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

}
