package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxpaiketable;
import com.xwcloud.cloud.stu.Dao.IPxpaiketableDao;
import com.xwcloud.cloud.stu.Service.IPxpaiketableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
@Service
public class PxpaiketableServiceImpl extends ServiceImpl<IPxpaiketableDao, Pxpaiketable> implements IPxpaiketableService {

    @Autowired
    IPxpaiketableDao iPxpaiketableDao;

    @Override
    public List<Pxpaiketable> getTk(Long classID, Date hvdate, Long qiyeID) {
        return iPxpaiketableDao.getTk(classID, hvdate, qiyeID);
    }

    @Override
    public List<Pxpaiketable> getpkBYClassID(Long classID, Long qiyeID) {
        return iPxpaiketableDao.getpkBYClassID(classID, qiyeID);
    }

    @Override
    public List<Pxpaiketable> getBykq(Long classID, Long qiyeID) {
        return iPxpaiketableDao.getBykq(classID, qiyeID);
    }

    @Override
    public List<Pxpaiketable> getBykc(Long kechengID, Long qiyeID) {
        return iPxpaiketableDao.getBykc(kechengID, qiyeID);
    }
}
