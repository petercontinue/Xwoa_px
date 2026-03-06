package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.WscTixian;
import com.xwcloud.cloud.wsc.Dao.IWscTixianDao;
import com.xwcloud.cloud.wsc.Service.IWscTixianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-23
 */
@Service
public class WscTixianServiceImpl extends ServiceImpl<IWscTixianDao, WscTixian> implements IWscTixianService {
    @Autowired
    IWscTixianDao iWscTixianDao;

    @Override
    public Page<HashMap<String,Object>> gettixianPage(Page page, QueryWrapper queryWrapper) {
        return iWscTixianDao.gettixianPage(page, queryWrapper);
    }
}
