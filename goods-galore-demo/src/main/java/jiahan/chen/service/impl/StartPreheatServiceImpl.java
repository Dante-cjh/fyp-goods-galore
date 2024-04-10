package jiahan.chen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import jiahan.chen.constant.GoodsConstants;
import jiahan.chen.core.cache.LocalCache;
import jiahan.chen.entity.Account;
import jiahan.chen.entity.Category;
import jiahan.chen.entity.Product;
import jiahan.chen.dto.resp.AccountRespDTO;
import jiahan.chen.dto.resp.CategoryRespDTO;
import jiahan.chen.dto.resp.ProductRespDTO;
import jiahan.chen.service.ICategoryService;
import jiahan.chen.service.IProductService;
import jiahan.chen.service.IStartPreheatService;
import jiahan.chen.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * @author Jiahan Chen
 * @ClassName StartPreheatServiceImpl
 */
@Service
@Slf4j
public class StartPreheatServiceImpl implements IStartPreheatService {
    @Autowired
    private IAccountService iAccountService;

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private IProductService iProductService;

    @Override
    public void initData() {
        Long startTime = System.currentTimeMillis();
        // 1.初始化 账户，商品分类，商品 加入缓存中
        initAccount();
        initCategory();
        initProduct();
        Long endTime = System.currentTimeMillis();
        log.info("初始化课程分类耗时:{}", endTime - startTime);
    }

    public void initAccount() {
        List<Account> allAccount = iAccountService.getAllAccount();
        // 将do对象转化为dto对象
        List<AccountRespDTO> accountRespDTOS = BeanUtil.copyToList(allAccount, AccountRespDTO.class);
        LocalCache.put(GoodsConstants.ALL_ACCOUNTLIST, accountRespDTOS);
        log.info("1.初始化数据【accountList: {}】 完成", allAccount);
    }

    public void initCategory() {
        // 1.将db中的商品分类数据 缓存jvm一级缓存中
        List<Category> allCategoryList = iCategoryService.getAllCategory();
        // 2.将db对象转化为dto对象
        if(CollectionUtils.isEmpty(allCategoryList)){
            log.error("[从db中未查找到allCategoryList数据]");
            return;
        }
        List<CategoryRespDTO> catagoryRespDTOList = BeanUtil.copyToList(allCategoryList, CategoryRespDTO.class);
        LocalCache.put(GoodsConstants.GOODS_ALLCATAGORYLIST, catagoryRespDTOList);
    }

    public void initProduct() {
        // 1.将db中的商品数据 缓存jvm一级缓存中
        List<Product> allProductList = iProductService.getAllProduct();
        // 2.将db对象转化为dto对象
        if(CollectionUtils.isEmpty(allProductList)){
            log.error("[从db中未查找到allProductList数据]");
            return;
        }
        List<ProductRespDTO> productRespDTOList = BeanUtil.copyToList(allProductList, ProductRespDTO.class);
        LocalCache.put(GoodsConstants.GOODS_ALLPRODUCTLIST, productRespDTOList);
    }
}
