package jiahan.chen.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.PageUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import jiahan.chen.base.BaseApiController;
import jiahan.chen.base.BaseResponse;
import jiahan.chen.constant.GoodsConstants;
import jiahan.chen.core.cache.LocalCache;
import jiahan.chen.dto.req.Product2ReqDTO;
import jiahan.chen.dto.req.ProductReqDTO;
import jiahan.chen.dto.resp.ProductRespDTO;
import jiahan.chen.entity.Product;
import jiahan.chen.entity.Supplier;
import jiahan.chen.service.IProductService;
import jiahan.chen.service.ISupplierService;
import jiahan.chen.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Jiahan Chen
 * @ClassName ProductController
 */
@RestController
@RequestMapping("/product")
@ApiOperation(value = "商品", notes = "商品")
@Slf4j
public class ProductController extends BaseApiController {

    @Autowired
    private IProductService iProductService;

    @Autowired
    private ISupplierService iSupplierService;

    /**
     * 查找
     * @return
     */
    @GetMapping("/getAllProduct")
    @ApiOperation(value = "获取所有商品", notes = "获取所有商品")
    public BaseResponse getAllCategory() {
        List<ProductRespDTO> productRespDTOList = new ArrayList<>();
        productRespDTOList = LocalCache.get(GoodsConstants.GOODS_ALLPRODUCTLIST, productRespDTOList);
        if (CollectionUtils.isEmpty(productRespDTOList)) {
            log.error("[从缓存中为查找到productRespDTOList数据]");
            return setResultError("未查找数据");
        }
        return setResultSuccessData(productRespDTOList);
    }

    @PostMapping("/searchProductList")
    @ApiOperation(value = "根据条件搜索商品", notes = "根据条件搜索商品")
    public BaseResponse searchProductList(@RequestBody ProductReqDTO productReqDTO) {
        List<ProductRespDTO> productRespDTOList = iProductService.searchCacheProductList(productReqDTO);
        if (productRespDTOList == null) {
            log.error("[根据条件：{}，未查找到数据]", productReqDTO);
            return setResultError("未查找到数据");
        }

        // 计算总页数
        Integer totalSize = productRespDTOList.size();
        Integer totalPage = PageUtil.totalPage(totalSize, GoodsConstants.DEFAULT_PAGE_SIZE);
        int pageNo = productReqDTO.getPageNo();
        if (!(pageNo <= totalPage)) {
            log.error("[pageNo参数传递错误] {}", pageNo);
            return setResultError("[pageNo参数传递错误]");
        }
        // 分页，索引小于等于总页数，才返回列表.
        List<ProductRespDTO> listPageProduct = CollUtil.page(pageNo - 1, GoodsConstants.DEFAULT_PAGE_SIZE,
                productRespDTOList);
        // 返回最终数据
        HashMap result = new HashMap();
        result.put("pageNo", pageNo); //当前页面
        result.put("pageSize", GoodsConstants.DEFAULT_PAGE_SIZE); // 每页条数
        result.put("total", totalSize); // 总记录数
        result.put("totalPage", totalPage); // 总页数
        result.put("listPageProduct", listPageProduct); // 分页后数据
        return setResultSuccessData(result);
    }

    @PostMapping("/addProduct")
    @ApiOperation(value = "添加商品", notes = "添加商品")
    public BaseResponse addProduct(@RequestHeader String token ,@RequestBody Product2ReqDTO productReq2DTO) {
        Integer accountId = TokenUtils.getUserIdByToken(token);
        // 获取供应商信息
        Supplier supplier = iSupplierService.getSupplierByAccountId(accountId);
        // 判断该id是供应商
        if (supplier == null) {
            log.error("该用户不是供应商");
            return setResultError("该用户不是供应商");
        }
        Integer supplierId = supplier.getSupplierId();

        return iProductService.createProduct(supplierId, productReq2DTO) ? setResultSuccess() : setResultError("添加失败");
    }

    // 更新产品
    @PutMapping("/update/{productId}")
    @ApiOperation(value = "更新商品", notes = "更新商品")
    public BaseResponse updateProduct(@PathVariable Integer productId,@RequestBody Product2ReqDTO product2ReqDTO) {
        // 检查参数
        if (productId == null) {
            log.error("[参数校验] 产品id为空");
            return setResultError("商品id不能为空");
        }
        return iProductService.updateProduct(productId ,product2ReqDTO) ? setResultSuccess() : setResultError("更新失败");
    }

    // 删除产品
    @DeleteMapping("/delete/{productId}")
    public BaseResponse deleteProduct(@PathVariable Integer productId, @RequestHeader String token) {
        // 检查参数
        if(productId == null) {
            log.error("[参数校验] 产品id为空");
            return setResultError("商品id不能为空");
        }

        // 验证是否为自己的商品
        Integer accountId = TokenUtils.getUserIdByToken(token);
        Supplier supplier = iSupplierService.getSupplierByAccountId(accountId);

        Product product = iProductService.getProductByProductId(productId);
        if (product.getSupplierId() != supplier.getSupplierId()) {
            log.error("该商品不是自己的商品");
            return setResultError("该商品不是自己的商品");
        }

        return iProductService.deleteProduct(productId.intValue()) ? setResultSuccess() : setResultError("删除失败");
    }
}
