package jiahan.chen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jiahan.chen.dto.resp.SupermarketRespDTO;
import jiahan.chen.entity.Supermarket;
import jiahan.chen.mapper.SupermarketMapper;
import jiahan.chen.service.ISupermarketService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class SupermarketServiceImpl extends ServiceImpl<SupermarketMapper, Supermarket> implements ISupermarketService {
    @Autowired
    private SupermarketMapper supermarketMapper;

    @Override
    public List<SupermarketRespDTO> getAllSupermarket() {
        QueryWrapper<Supermarket> supermarketWrapper = new QueryWrapper<>();
        List<Supermarket> supermarkets = supermarketMapper.selectList(supermarketWrapper);
        return BeanUtil.copyToList(supermarkets, SupermarketRespDTO.class);
    }

    @Override
    public Supermarket getSupermarketByAccountId(Integer accountId) {
        QueryWrapper<Supermarket> supermarketWrapper = new QueryWrapper<>();
        supermarketWrapper.eq("account_id", accountId);
        Supermarket supermarket = supermarketMapper.selectOne(supermarketWrapper);
        return supermarket;
    }

    @Override
    public Supermarket getSupermarketBySupermarketId(Integer supermarketId) {
        return supermarketMapper.selectById(supermarketId);
    }
}
