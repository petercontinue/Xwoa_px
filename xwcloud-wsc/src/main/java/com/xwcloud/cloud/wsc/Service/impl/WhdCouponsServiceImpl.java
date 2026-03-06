package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.WhdCoupons;

import com.xwcloud.cloud.wsc.Dao.IWhdCouponsDao;
import com.xwcloud.cloud.wsc.Service.IWhdCouponsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-25
 */
@Service
public class WhdCouponsServiceImpl extends ServiceImpl<IWhdCouponsDao, WhdCoupons> implements IWhdCouponsService {
    @Autowired
    IWhdCouponsDao iWhdCouponsDao;

    @Override
    public Page<HashMap<String, Object>> GetCouponsPages(Page page, QueryWrapper wrapper) {
        return iWhdCouponsDao.GetCouponsPages(page, wrapper);
    }
}
