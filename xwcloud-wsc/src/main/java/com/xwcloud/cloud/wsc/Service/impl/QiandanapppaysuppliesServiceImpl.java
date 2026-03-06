package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Qiandanapppaysupplies;

import com.xwcloud.cloud.wsc.Dao.IQiandanapppaysuppliesDao;
import com.xwcloud.cloud.wsc.Service.IQiandanapppaysuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-28
 */
@Service
public class QiandanapppaysuppliesServiceImpl extends ServiceImpl<IQiandanapppaysuppliesDao, Qiandanapppaysupplies> implements IQiandanapppaysuppliesService {
    @Autowired
    IQiandanapppaysuppliesDao iQiandanapppaysuppliesDao;
}
