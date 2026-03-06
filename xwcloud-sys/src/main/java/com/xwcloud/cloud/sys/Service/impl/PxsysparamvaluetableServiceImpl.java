package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxsysparamvaluetable;
import com.xwcloud.cloud.sys.Dao.IPxsysparamvaluetableDao;
import com.xwcloud.cloud.sys.Service.IPxsysparamvaluetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-30
 */
@Service
public class PxsysparamvaluetableServiceImpl extends ServiceImpl<IPxsysparamvaluetableDao, Pxsysparamvaluetable> implements IPxsysparamvaluetableService {
    @Autowired
    IPxsysparamvaluetableDao iPxsysparamvaluetableDao;

    @Override
    public Pxsysparamvaluetable GetPxsysparamvalueByQiyeIDAndValueID(Long qiyeID, Long ValueID) {
        return iPxsysparamvaluetableDao.GetPxsysparamvalueByQiyeIDAndValueID(qiyeID, ValueID);
    }
}
