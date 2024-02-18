package jiahan.chen.controller;

import io.swagger.annotations.ApiOperation;
import jiahan.chen.base.BaseApiController;
import jiahan.chen.base.BaseResponse;
import jiahan.chen.constant.GoodsConstants;
import jiahan.chen.core.cache.LocalCache;
import jiahan.chen.dto.resp.CategoryRespDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiahan Chen
 * @ClassName CategoryController
 */
@RestController
@RequestMapping("/category")
@ApiOperation(value = "商品分类", notes = "商品分类")
@Slf4j
public class CategoryController extends BaseApiController {
    /**
     * 查找
     * @return
     */
    @GetMapping("/getAllCategory")
    @ApiOperation(value = "Get all categories", notes = "Get all categories")
    public BaseResponse getAllCategory() {
        List<CategoryRespDTO> categoryRespDtoList = new ArrayList<>();
        categoryRespDtoList = LocalCache.get(GoodsConstants.GOODS_ALLCATAGORYLIST, categoryRespDtoList);
        if (CollectionUtils.isEmpty(categoryRespDtoList)) {
            log.error("[从缓存中为查找到categoryRespDtoList数据]");
            return setResultError("未查找数据");
        }
        return setResultSuccessData(categoryRespDtoList);
    }


}
