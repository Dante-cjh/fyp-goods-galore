package jiahan.chen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jiahan.chen.dto.resp.ProductRespDTO;
import jiahan.chen.entity.Product;
import jiahan.chen.entity.Supplier;
import jiahan.chen.mapper.ProductMapper;
import jiahan.chen.mapper.SupplierMapper;
import jiahan.chen.service.ISupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-05
 */
@Service
@Slf4j
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements ISupplierService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public Supplier getSupplierBySupplierId(Integer supplierId) {
        return supplierMapper.selectById(supplierId);
    }

    @Override
    public Supplier getSupplierByAccountId(Integer accountId) {
        LambdaQueryWrapper<Supplier> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Supplier::getAccountId, accountId);
        return supplierMapper.selectOne(lambdaQueryWrapper);
    }

    @Override
    public List<ProductRespDTO> getAllProducts(Integer supplierId) {
        QueryWrapper<Product> productWrapper = new QueryWrapper<>();
        productWrapper.eq("supplier_id", supplierId);
        List<Product> productList = productMapper.selectList(productWrapper);

        if (CollectionUtils.isEmpty(productList)) {
            log.error("[从缓存中未查找到productList数据]");
            return null;
        }

        // 将do对象转化为dto对象
        return BeanUtil.copyToList(productList, ProductRespDTO.class);
    }
}
