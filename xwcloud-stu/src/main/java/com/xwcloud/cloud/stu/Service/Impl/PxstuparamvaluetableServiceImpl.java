package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxstuparamvaluetable;

import com.xwcloud.cloud.stu.Dao.IPxstuparamvaluetableDao;
import com.xwcloud.cloud.stu.Service.IPxstuparamvaluetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
 */
@Service
public class PxstuparamvaluetableServiceImpl extends ServiceImpl<IPxstuparamvaluetableDao, Pxstuparamvaluetable> implements IPxstuparamvaluetableService {

    @Autowired
    IPxstuparamvaluetableDao iPxstuparamvaluetableDao;

    @Override
    public List<Pxstuparamvaluetable> getstuParam(Long tid, Long stuID, Long qiyeID) {
        return iPxstuparamvaluetableDao.getstuParam(tid, stuID, qiyeID);
    }

    @Override
    public List<Pxstuparamvaluetable> selectstuparamvalue(QueryWrapper queryWrapper) {
        return iPxstuparamvaluetableDao.selectstuparamvalue(queryWrapper);
    }
}
