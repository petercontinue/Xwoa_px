package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.Vo.collectVo;
import com.xwcloud.cloud.model.entity.WscCollect;
import com.xwcloud.cloud.wsc.Dao.IWscCollectDao;
import com.xwcloud.cloud.wsc.Service.IWscCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-26
 */
@Service
public class WscCollectServiceImpl extends ServiceImpl<IWscCollectDao, WscCollect> implements IWscCollectService {
    @Autowired
    IWscCollectDao iWscCollectDao;

    @Override
    public Page<collectVo> GetwscUserCollect(Page page, QueryWrapper wrapper) {
        return iWscCollectDao.GetwscUserCollect(page, wrapper);
    }
}
