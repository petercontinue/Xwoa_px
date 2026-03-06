package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.Pxbuxikechengtable;
import com.xwcloud.cloud.pkxk.Dao.IPxbuxikechengtableDao;
import com.xwcloud.cloud.pkxk.Service.IPxbuxikechengtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class PxbuxikechengtableServiceImpl extends ServiceImpl<IPxbuxikechengtableDao, Pxbuxikechengtable> implements IPxbuxikechengtableService {
    @Autowired
    IPxbuxikechengtableDao iPxbuxikechengtableDao;

    @Override
    public List<Pxbuxikechengtable> selectbuxikecheng(QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.selectbuxikecheng(queryWrapper);
    }

    @Override
    public List<ExportReKeshiVo> ExportRekeshi(QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.ExportRekeshi(queryWrapper);
    }

    @Override
    public List<Pxbuxikechengtable> getBxByStuAndClass(Long stuID, Long classID, Long qiyeID) {
        return iPxbuxikechengtableDao.getBxByStuAndClass(stuID, classID, qiyeID);
    }

    @Override
    public Page<RemainkeshiDetailsVo> getRemainkeshiDetailsPage(Page page, QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.getRemainkeshiDetailsPage(page, queryWrapper);
    }

    @Override
    public Page<RemainDaysVo> getRemainDaysPage(Page page, QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.getRemainDaysPage(page, queryWrapper);
    }

    @Override
    public Page<UpdatekeshiAndXFVo> getUpdatekeshiAndXFPage(Page page, QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.getUpdatekeshiAndXFPage(page, queryWrapper);
    }

    @Override
    public List<SumbxRemainVo> getSumRks(Long stuID, Long kechengID, Long qiyeID) {
        return iPxbuxikechengtableDao.getSumRks(stuID, kechengID, qiyeID);
    }

    @Override
    public Page<AutoxiaokeVO> getAutoPage(Page page, QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.getAutoPage(page, queryWrapper);
    }

    @Override
    public List<AutoxiaokeVO> ExportAutoPage(QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.ExportAutoPage(queryWrapper);
    }

    @Override
    public List<classstuCountVo> getstuCount(Long paike) {
        return iPxbuxikechengtableDao.getstuCount(paike);
    }

    @Override
    public List<Pxbuxikechengtable> getbuxi(Long stuID, Long paikeID, Long qiyeID) {
        return iPxbuxikechengtableDao.getbuxi(stuID, paikeID, qiyeID);
    }

    @Override
    public Page<classkaoqingVo> getclasskaoqingPage(Page page, Long paikeID, Long classID) {
        return iPxbuxikechengtableDao.getclasskaoqingPage(page, paikeID, classID);
    }

    @Override
    public List<classstuCountVo> getrengongstuCount(Long paike) {
        return iPxbuxikechengtableDao.getrengongstuCount(paike);
    }

    @Override
    public List<Pxbuxikechengtable> getbxbystuclass(Long stuID, Long classID) {
        return iPxbuxikechengtableDao.getbxbystuclass(stuID, classID);
    }

    @Override
    public List<SumbxRemainVo> getSumzongRks(Long stuID) {
        return iPxbuxikechengtableDao.getSumzongRks(stuID);
    }

    @Override
    public List<Pxbuxikechengtable> getbuxiBystuAcla(Long stuID, Long classID) {
        return iPxbuxikechengtableDao.getbuxiBystuAcla(stuID, classID);
    }

    @Override
    public List<nopaikegetStuVo> getStuNoPaike(String haveclassDate, String startClassDateTime, String endClassDateTime, Long classID,QueryWrapper queryWrapper) {
        return iPxbuxikechengtableDao.getStuNoPaike(haveclassDate, startClassDateTime, endClassDateTime, classID,queryWrapper);
    }

    @Override
    public List<nopaikegetStuVo> getaddstuList(Long stuID, Long buxiID) {
        return iPxbuxikechengtableDao.getaddstuList(stuID, buxiID);
    }

    @Override
    public String getbxremainkeshi(Wrapper wrapper) {
        return iPxbuxikechengtableDao.getbxremainkeshi(wrapper);
    }


}
