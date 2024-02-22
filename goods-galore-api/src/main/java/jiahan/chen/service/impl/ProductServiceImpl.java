package jiahan.chen.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jiahan.chen.constant.GoodsConstants;
import jiahan.chen.core.cache.LocalCache;
import jiahan.chen.dto.req.Product2ReqDTO;
import jiahan.chen.dto.req.ProductReqDTO;
import jiahan.chen.dto.resp.ProductRespDTO;
import jiahan.chen.entity.Product;
import jiahan.chen.mapper.ProductMapper;
import jiahan.chen.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jiahan.chen.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
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

    @Override
    public Product getProductByProductId(Integer productId) {
        QueryWrapper<Product> productWrapper = new QueryWrapper<>();
        productWrapper.eq("product_id", productId);
        return productMapper.selectOne(productWrapper);
    }

    @Override
    @Transactional
    public boolean createProduct(Integer supplierId, Product2ReqDTO product2ReqDTO) {
        // 获取参数并且检查
        String name = product2ReqDTO.getName();
        if (StringUtils.isEmpty(name)) {
            log.error("[参数校验] 产品名称为空");
            return false;
        }

        Integer categoryId = product2ReqDTO.getCategoryId();
        if (categoryId == null) {
            log.error("[参数校验] 产品类型为空");
            return false;
        }

        String productPic = null;
        MultipartFile picFile = product2ReqDTO.getProductPic();
        if (picFile != null) {
            productPic = FileUtils.upload(product2ReqDTO.getProductPic());
        }

        String description = product2ReqDTO.getDescription();
        BigDecimal price = product2ReqDTO.getPrice();
        String unit = product2ReqDTO.getUnit();

        // 上传数据
        Product product = new Product();
        product.setName(name);
        product.setCategoryId(categoryId);
        product.setProductPic(productPic);
        product.setDescription(description);
        product.setPrice(price);
        product.setUnit(unit);
        product.setSupplierId(supplierId);

        return productMapper.insert(product) > GoodsConstants.DB_INSERT_RESULT_BIGZERO;
    }

    @Override
    @Transactional
    public boolean updateProduct(Integer productId, Product2ReqDTO product2ReqDTO) {
        Product product = productMapper.selectById(productId);
        if (product == null) {
            log.error("[参数校验] 该商品不存在");
            return false;
        }

        // 获取新商品信息
        String name = product2ReqDTO.getName();
        String description = product2ReqDTO.getDescription();
        BigDecimal price = product2ReqDTO.getPrice();
        String unit = product2ReqDTO.getUnit();
        String productPic = null;
        MultipartFile picFile = product2ReqDTO.getProductPic();
        if (picFile != null) {
            productPic = FileUtils.upload(product2ReqDTO.getProductPic());
        }

        // 更新数据
        if (!StringUtils.isEmpty(name)) {
            product.setName(name);
        }
        if (!StringUtils.isEmpty(description)) {
            product.setDescription(description);
        }
        if (price != null) {
            product.setPrice(price);
        }
        if (!StringUtils.isEmpty(unit)) {
            product.setUnit(unit);
        }
        if (!StringUtils.isEmpty(productPic)) {
            product.setProductPic(productPic);
        }

        return productMapper.updateById(product) > GoodsConstants.DB_UPDATE_RESULT_BIGZERO;
    }

    @Override
    public boolean deleteProduct(Integer productId) {
        Product product = productMapper.selectById(productId);
        if (product == null) {
            log.error("[参数校验] 该商品不存在");
            return false;
        }
        return productMapper.deleteById(productId) > GoodsConstants.DB_UPDATE_RESULT_BIGZERO;
    }
}
