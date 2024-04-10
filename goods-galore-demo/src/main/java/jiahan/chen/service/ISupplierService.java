package jiahan.chen.service;

import io.swagger.models.auth.In;
import jiahan.chen.dto.resp.ProductRespDTO;
import jiahan.chen.dto.resp.SupplierRespDTO;
import jiahan.chen.entity.Supplier;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-17
 */
public interface ISupplierService extends IService<Supplier> {
    Supplier getSupplierBySupplierId(Integer supplierId);

    Supplier getSupplierByAccountId(Integer accountId);

    List<ProductRespDTO> getAllProducts(Integer supplierId);

    List<SupplierRespDTO> getAllSupplier();
}
