package jiahan.chen.service;

import jiahan.chen.dto.req.Product2ReqDTO;
import jiahan.chen.dto.req.ProductReqDTO;
import jiahan.chen.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import jiahan.chen.dto.resp.ProductRespDTO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-05
 */
public interface IProductService extends IService<Product> {

    List<Product> getAllProduct();

    List<ProductRespDTO> searchCacheProductList(ProductReqDTO productReqDTO);

    // 根据id获得商品
    Product getProductByProductId(Integer productId);


    // 上传产品
    boolean createProduct(Integer supplierId, Product2ReqDTO product2ReqDTO);

    // 更新产品
    boolean updateProduct(Integer productId, Product2ReqDTO product2ReqDTO);

    // 删除产品
    boolean deleteProduct(Integer productId);
}
