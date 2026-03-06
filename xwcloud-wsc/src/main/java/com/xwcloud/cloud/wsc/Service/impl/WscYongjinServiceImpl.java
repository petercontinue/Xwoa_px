package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.WscYongjin;
import com.xwcloud.cloud.wsc.Dao.IWscYongjinDao;
import com.xwcloud.cloud.wsc.Service.IWscYongjinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-02-22
 */
@Service
public class WscYongjinServiceImpl extends ServiceImpl<IWscYongjinDao, WscYongjin> implements IWscYongjinService {

    @Autowired
    IWscYongjinDao wscYongjinDao;

    @Override
    public Page<HashMap<String, Object>> getfangyongShowPages(Page page, QueryWrapper wrapper) {
        return wscYongjinDao.getfangyongShowPages(page, wrapper);
    }

    @Override
    public Page<HashMap<String, Object>> getbuytuikePages(Page page, QueryWrapper queryWrapper) {
        return wscYongjinDao.getbuytuikePages(page, queryWrapper);
    }

    @Override
    public Page<HashMap<String, Object>> gettkteamPage(Page page, QueryWrapper queryWrapper) {
        return wscYongjinDao.gettkteamPage(page, queryWrapper);
    }

    @Override
    public Page<HashMap<String, Object>> getfanyongpaiming(Page page, QueryWrapper queryWrapper) {
        return wscYongjinDao.getfanyongpaiming(page, queryWrapper);
    }
}
