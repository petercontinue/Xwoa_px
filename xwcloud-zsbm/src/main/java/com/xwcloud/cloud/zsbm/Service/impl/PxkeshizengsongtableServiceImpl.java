package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxkeshizengsongtable;
import com.xwcloud.cloud.zsbm.Dao.IPxkeshizengsongtableDao;
import com.xwcloud.cloud.zsbm.Service.IPxkeshizengsongtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-13
 */
@Service
public class PxkeshizengsongtableServiceImpl extends ServiceImpl<IPxkeshizengsongtableDao, Pxkeshizengsongtable> implements IPxkeshizengsongtableService {

    @Autowired
    IPxkeshizengsongtableDao iPxkeshizengsongtableDao;

    @Override
    public List<Pxkeshizengsongtable> GetZengsongRecords(QueryWrapper wrapper) {
        return iPxkeshizengsongtableDao.GetZengsongRecords(wrapper);
    }

    @Override
    public Pxkeshizengsongtable GetZongSongInfo(Long qiandanInfoID, Long kechengId, BigDecimal kechengPrice) {
        return iPxkeshizengsongtableDao.GetZongSongInfo(qiandanInfoID, kechengId, kechengPrice);
    }

    @Override
    public Integer DeleteKeshizengsongByStuId(Long stuID,Long qiyeID) {
        return iPxkeshizengsongtableDao.DeleteKeshizengsongByStuId(stuID,qiyeID);
    }

    @Override
    public List<Pxkeshizengsongtable> GetZengsongkeshiByQiandanID(Long qiandanInfoID) {
        return iPxkeshizengsongtableDao.GetZengsongkeshiByQiandanID(qiandanInfoID);
    }

    @Override
    public Integer DeleteKeshizengsongByqiandanInfoID(Long qiandanInfoID,Long qiyeID) {
        return iPxkeshizengsongtableDao.DeleteKeshizengsongByqiandanInfoID(qiandanInfoID,qiyeID);
    }
}
