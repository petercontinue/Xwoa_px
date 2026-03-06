package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.Vo.buyKechengVo;
import com.xwcloud.cloud.model.entity.Pxbuxikechengtable;
import com.xwcloud.cloud.zsbm.Dao.IPxbuxikechengtableDao;
import com.xwcloud.cloud.zsbm.Service.IPxbuxikechengtableService;
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
 * @since 2020-11-11
 */
@Service
public class PxbuxikechengtableServiceImpl extends ServiceImpl<IPxbuxikechengtableDao, Pxbuxikechengtable> implements IPxbuxikechengtableService {

    @Autowired
    IPxbuxikechengtableDao iPxbuxikechengtableDao;

    @Override
    public Pxbuxikechengtable GetZidingYiKecheng(QueryWrapper wrapper) {
        return iPxbuxikechengtableDao.GetZidingYiKecheng(wrapper);
    }

    @Override
    public List<Pxbuxikechengtable> GetBuxikchengByStuID(Long stuID,Long qiyeID) {
        return iPxbuxikechengtableDao.GetBuxikchengByStuID(stuID,qiyeID);
    }

    @Override
    public List<Pxbuxikechengtable> GetStuBuxikechengList(Long stuID, Long kechengID,Long qiyeID) {
        return iPxbuxikechengtableDao.GetStuBuxikechengList(stuID, kechengID, qiyeID);
    }

    @Override
    public Pxbuxikechengtable GetBuxikecheng(Long stuID, Long kechengID, BigDecimal kechengprice,Long qiyeID) {
        return iPxbuxikechengtableDao.GetBuxikecheng(stuID, kechengID, kechengprice, qiyeID);
    }

    @Override
    public Pxbuxikechengtable GetBuyBuxikecheng(Long stuID, Long kechengID, BigDecimal kechengprice,Long qiyeID) {
        return iPxbuxikechengtableDao.GetBuyBuxikecheng(stuID, kechengID, kechengprice,qiyeID);
    }

    @Override
    public Integer deleteBuxikechengByStuID(Long stuID,Long qiyeID) {
        return iPxbuxikechengtableDao.deleteBuxikechengByStuID(stuID,qiyeID);
    }

    @Override
    public List<Pxbuxikechengtable> getbuxikechenglist(Long stuID, Long buxiID,Long qiyeID) {
        return iPxbuxikechengtableDao.getbuxikechenglist(stuID,buxiID,qiyeID);
    }

    @Override
    public List<buyKechengVo> GetAllStukechengInfoList(long stuID, Long qiyeID) {
        return iPxbuxikechengtableDao.GetAllStukechengInfoList(stuID,qiyeID);
    }


    /**
     * -------------------------------------------意向学员----------------------------------------
     */
    @Override
    public List<Pxbuxikechengtable> getAllBuxikechengByStuID(Long stuID) {
        return iPxbuxikechengtableDao.getAllBuxikechengByStuID(stuID);
    }

    @Override
    public Pxbuxikechengtable getBuxikechengByStuIDAndkechengID(Long stuID, Long kechnegID) {
        return iPxbuxikechengtableDao.getBuxikechengByStuIDAndkechengID(stuID, kechnegID);
    }

}
