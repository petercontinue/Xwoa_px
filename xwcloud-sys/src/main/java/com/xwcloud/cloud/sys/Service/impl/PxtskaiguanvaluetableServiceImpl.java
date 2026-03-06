package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.entity.Pxtskaiguanvaluetable;
import com.xwcloud.cloud.sys.Dao.IPxtskaiguanvaluetableDao;
import com.xwcloud.cloud.sys.Service.IPxtskaiguanvaluetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-08
 */
@Service
public class PxtskaiguanvaluetableServiceImpl extends ServiceImpl<IPxtskaiguanvaluetableDao, Pxtskaiguanvaluetable> implements IPxtskaiguanvaluetableService {

    @Autowired
    IPxtskaiguanvaluetableDao iPxtskaiguanvaluetableDao;
    @Override
    public Pxtskaiguanvaluetable GetPxtsKaiguanvalueById(long TSTypeID, long qiyeID) {
        return iPxtskaiguanvaluetableDao.GetPxtsKaiguanvalueById(TSTypeID,qiyeID);
    }
}
