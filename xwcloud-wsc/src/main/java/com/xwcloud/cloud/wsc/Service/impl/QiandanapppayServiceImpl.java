package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.entity.Qiandanapppay;
import com.xwcloud.cloud.wsc.Dao.IQiandanapppayDao;
import com.xwcloud.cloud.wsc.Service.IQiandanapppayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-28
 */
@Service
public class QiandanapppayServiceImpl extends ServiceImpl<IQiandanapppayDao, Qiandanapppay> implements IQiandanapppayService {
    @Autowired
    IQiandanapppayDao iQiandanapppayDao;

    @Override
    public Page<HashMap<String, Object>> GetUserpayPage(Page page, QueryWrapper wrapper) {
        return iQiandanapppayDao.GetUserpayPage(page, wrapper);
    }
}
