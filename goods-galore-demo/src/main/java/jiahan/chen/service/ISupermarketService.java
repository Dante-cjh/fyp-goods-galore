package jiahan.chen.service;

import jiahan.chen.dto.resp.SupermarketRespDTO;
import jiahan.chen.entity.Supermarket;
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
public interface ISupermarketService extends IService<Supermarket> {

    List<SupermarketRespDTO> getAllSupermarket();

    Supermarket getSupermarketByAccountId(Integer accountId);

    Supermarket getSupermarketBySupermarketId(Integer supermarketId);
}
