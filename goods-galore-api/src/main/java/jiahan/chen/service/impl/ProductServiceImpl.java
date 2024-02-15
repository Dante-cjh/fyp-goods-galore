package jiahan.chen.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jiahan.chen.constant.GoodsConstants;
import jiahan.chen.core.cache.LocalCache;
import jiahan.chen.dto.req.ProductReqDTO;
import jiahan.chen.dto.resp.ProductRespDTO;
import jiahan.chen.entity.Product;
import jiahan.chen.mapper.ProductMapper;
import jiahan.chen.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-05
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<Product> getAllProduct() {
        QueryWrapper<Product> productWrapper = new QueryWrapper<>();
        List<Product> productList = productMapper.selectList(productWrapper);
        return productList;
    }

    @Override
    public List<ProductRespDTO> searchCacheProductList(ProductReqDTO productReqDTO) {
        // 从缓存中获取数据
        List<ProductRespDTO> productRespDTOList = new ArrayList<>();
        productRespDTOList = LocalCache.get(
                GoodsConstants.GOODS_ALLPRODUCTLIST,
                productRespDTOList);
        if (CollectionUtils.isEmpty(productRespDTOList)) {
            log.error("[从缓存中为查找到productRespDTOList数据]");
            return null;
        }
        // 根据条件搜索
        Stream<ProductRespDTO> stream = productRespDTOList.stream();
        String productName = productReqDTO.getName();
        // 根据课程名称搜索
        if (!StringUtils.isEmpty(productName)) {
            stream = stream.filter(c ->
                    StrUtil.containsIgnoreCase(c.getName(), (productName)));
        }
        // 根据分类id查找
        Integer categoryId = productReqDTO.getCategoryId();
        if (categoryId != null) {
            stream = stream.filter((c) -> c.getCategoryId().equals(categoryId));
        }
        // 根据供应商id查找
        Integer supplierId = productReqDTO.getSupplierId();
        if (supplierId != null) {
            stream = stream.filter((c) -> c.getSupplierId().equals(supplierId));
        }
        //根据最后更新时间排序 返回对应的list集合
        return stream.sorted(Comparator.comparing(ProductRespDTO::getCategoryId))
                .collect(Collectors.toList());
    }
}
