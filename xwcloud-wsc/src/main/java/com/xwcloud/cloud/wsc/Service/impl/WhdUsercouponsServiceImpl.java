package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.entity.WhdUsercoupons;
import com.xwcloud.cloud.wsc.Dao.IWhdUsercouponsDao;
import com.xwcloud.cloud.wsc.Service.IWhdUsercouponsService;
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
public class WhdUsercouponsServiceImpl extends ServiceImpl<IWhdUsercouponsDao, WhdUsercoupons> implements IWhdUsercouponsService {
    @Autowired
    IWhdUsercouponsDao iWhdUsercouponsDao;

    @Override
    public Page<HashMap<String, Object>> GetwscUsercouponsPage(Page page, QueryWrapper wrapper) {
        return iWhdUsercouponsDao.GetwscUsercouponsPage(page, wrapper);
    }
}
