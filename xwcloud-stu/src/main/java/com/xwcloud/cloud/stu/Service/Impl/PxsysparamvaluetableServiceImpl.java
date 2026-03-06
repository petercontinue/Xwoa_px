package com.xwcloud.cloud.stu.Service.Impl;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxsysparamvaluetable;
import com.xwcloud.cloud.stu.Dao.IPxsysparamvaluetableDao;
import com.xwcloud.cloud.stu.Service.IPxsysparamvaluetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-15
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
