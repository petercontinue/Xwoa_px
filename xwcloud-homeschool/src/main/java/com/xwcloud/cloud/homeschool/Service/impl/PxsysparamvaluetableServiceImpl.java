package com.xwcloud.cloud.homeschool.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.homeschool.Dao.IPxsysparamvaluetableDao;
import com.xwcloud.cloud.homeschool.Service.IPxsysparamvaluetableService;
import com.xwcloud.cloud.model.entity.Pxsysparamvaluetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-07
 */
@Service
public class PxsysparamvaluetableServiceImpl extends ServiceImpl<IPxsysparamvaluetableDao, Pxsysparamvaluetable> implements IPxsysparamvaluetableService {

    @Autowired
    IPxsysparamvaluetableDao iPxsysparamvaluetableDao;

    @Override
    public List<Pxsysparamvaluetable> selectsysvalue(QueryWrapper queryWrapper) {
        return iPxsysparamvaluetableDao.selectsysvalue(queryWrapper);
    }

    @Override
    public Pxsysparamvaluetable getsysvalue(Long qiyeID, Long sysparamTypeID) {
        return iPxsysparamvaluetableDao.getsysvalue(qiyeID, sysparamTypeID);
    }
}
