package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.WscDongtaiinfo;
import com.xwcloud.cloud.zsbm.Dao.IWscDongtaiinfoDao;
import com.xwcloud.cloud.zsbm.Service.IWscDongtaiinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-06
 */
@Service
public class WscDongtaiinfoServiceImpl extends ServiceImpl<IWscDongtaiinfoDao, WscDongtaiinfo> implements IWscDongtaiinfoService {

    @Autowired
    IWscDongtaiinfoDao iWscDongtaiinfoDao;

    @Override
    public Page<HashMap<String, String>> getAllWscUserDongtaiPages(Page page, QueryWrapper wrapper) {
        return iWscDongtaiinfoDao.getAllWscUserDongtaiPages(page, wrapper);
    }

    @Override
    public Page<HashMap<String, String>> GetPagesDianzanInfo(Page page, QueryWrapper wrapper) {
        return iWscDongtaiinfoDao.GetPagesDianzanInfo(page, wrapper);
    }

    @Override
    public Page<HashMap<String, String>> GetPagesPinglunInfos(Page page, QueryWrapper queryWrapper) {
        return iWscDongtaiinfoDao.GetPagesPinglunInfos(page, queryWrapper);
    }

}
