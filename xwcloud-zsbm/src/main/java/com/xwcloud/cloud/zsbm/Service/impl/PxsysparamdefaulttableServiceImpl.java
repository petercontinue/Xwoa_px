package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxsysparamdefaulttable;
import com.xwcloud.cloud.zsbm.Dao.IPxsysparamdefaulttableDao;
import com.xwcloud.cloud.zsbm.Service.IPxsysparamdefaulttableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
@Service
public class PxsysparamdefaulttableServiceImpl extends ServiceImpl<IPxsysparamdefaulttableDao, Pxsysparamdefaulttable> implements IPxsysparamdefaulttableService {

    @Autowired
    IPxsysparamdefaulttableDao iPxsysparamdefaulttableDao;

    @Override
    public Pxsysparamdefaulttable GetSysParamById(long Id) {
        return iPxsysparamdefaulttableDao.GetSysParamById(Id);
    }
}
