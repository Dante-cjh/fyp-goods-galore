package jiahan.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jiahan.chen.entity.Supplier;
import jiahan.chen.mapper.SupplierMapper;
import jiahan.chen.service.ISupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-05
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements ISupplierService {

    @Override
    public Supplier getSupplierBySupplierId(Integer supplierId) {
        return baseMapper.selectById(supplierId);
    }

    @Override
    public Supplier getSupplierByAccountId(Integer accountId) {
        LambdaQueryWrapper<Supplier> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Supplier::getAccountId, accountId);
        return baseMapper.selectOne(lambdaQueryWrapper);
    }
}
