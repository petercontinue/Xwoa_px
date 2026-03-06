package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.entity.WscOrdertuifei;
import com.xwcloud.cloud.wsc.Dao.IWscOrdertuifeiDao;
import com.xwcloud.cloud.wsc.Service.IWscOrdertuifeiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-02-23
 */
@Service
public class WscOrdertuifeiServiceImpl extends ServiceImpl<IWscOrdertuifeiDao, WscOrdertuifei> implements IWscOrdertuifeiService {

    @Autowired
    IWscOrdertuifeiDao wscOrdertuifeiDao;

    @Override
    public Page<HashMap<String, Object>> getTuihuokuanPage(Page page, QueryWrapper wrapper) {
        return wscOrdertuifeiDao.getTuihuokuanPage(page, wrapper);
    }
}
