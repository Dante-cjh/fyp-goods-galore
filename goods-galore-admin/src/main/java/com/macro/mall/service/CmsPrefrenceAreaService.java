package com.macro.mall.service;

import com.macro.mall.model.CmsPrefrenceArea;

import java.util.List;

/**
 * 商品优选管理Service
 * Created by Jiahan Chen
 */
public interface CmsPrefrenceAreaService {
    /**
     * 获取所有优选专区
     */
    List<CmsPrefrenceArea> listAll();
}
