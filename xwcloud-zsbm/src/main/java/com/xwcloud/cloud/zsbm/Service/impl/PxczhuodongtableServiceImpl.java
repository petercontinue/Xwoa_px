package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxczhuodongtable;
import com.xwcloud.cloud.zsbm.Dao.IPxczhuodongtableDao;
import com.xwcloud.cloud.zsbm.Service.IPxczhuodongtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-12
 */
@Service
public class PxczhuodongtableServiceImpl extends ServiceImpl<IPxczhuodongtableDao, Pxczhuodongtable> implements IPxczhuodongtableService {

    @Autowired
    IPxczhuodongtableDao iPxczhuodongtableDao;

    @Override
    public List<Pxczhuodongtable> GetChongzhiuhuodongByDate(String Date, long qiyeID) {
        return iPxczhuodongtableDao.GetChongzhiuhuodongByDate(Date,qiyeID);
    }
}
