package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.entity.WscShoppingcat;
import com.xwcloud.cloud.wsc.Dao.IWscShoppingcatDao;
import com.xwcloud.cloud.wsc.Service.IWscShoppingcatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-02-23
 */
@Service
public class WscShoppingcatServiceImpl extends ServiceImpl<IWscShoppingcatDao, WscShoppingcat> implements IWscShoppingcatService {
    @Autowired
    IWscShoppingcatDao wscShoppingcatDao;

    @Override
    public Page<HashMap<String, Object>> getShoppingCatPage(Page page, QueryWrapper wrapper) {
        return wscShoppingcatDao.getShoppingCatPage(page, wrapper);
    }

    @Override
    public Page<HashMap<String, Object>> getshoppingcartByApp(Page page, QueryWrapper wrapper) {
        return wscShoppingcatDao.getshoppingcartByApp(page, wrapper);
    }
}
