package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.Pxstutable;
import com.xwcloud.cloud.zsbm.Dao.IPxstutableDao;
import com.xwcloud.cloud.zsbm.Service.IPxstutableService;
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
 * @since 2020-11-12
 */
@Service
public class PxstutableServiceImpl extends ServiceImpl<IPxstutableDao, Pxstutable> implements IPxstutableService {

    @Autowired
    IPxstutableDao iPxstutableDao;

    @Override
    public Pxstutable GetYixiangStuByID(Long Id) {
        return iPxstutableDao.GetYixiangStuByID(Id);
    }

    @Override
    public Pxstutable updateyxstu(Long id, Long qiyeID) {
        return iPxstutableDao.updateyxstu(id, qiyeID);
    }

    @Override
    public int UpdateStuRemainChongzhi(BigDecimal remainChongzhi, Long stuID) {
        return iPxstutableDao.UpdateStuRemainChongzhi(remainChongzhi, stuID);
    }

    @Override
    public List<StuYueInfoVo> getAllChongzhiList(QueryWrapper wrapper) {
        return iPxstutableDao.getAllChongzhiList(wrapper);
    }

    @Override
    public List<Pxstutable> getStuByZidingyi(QueryWrapper wrapper) {
        return iPxstutableDao.getStuByZidingyi(wrapper);
    }

    @Override
    public List<StuyueVo> getAllStuyueList() {
        return iPxstutableDao.getAllStuyueList();
    }

    @Override
    public List<xufeistuVO> GetAllXufeistuList(QueryWrapper queryWrapper) {
        return iPxstutableDao.GetAllXufeistuList(queryWrapper);
    }

    @Override
    public List<qdkeshiVo> getqiandankeshi(QueryWrapper queryWrapper) {
        return iPxstutableDao.getqiandankeshi(queryWrapper);
    }

    @Override
    public List<qdCountVo> getqiandanCountlist(QueryWrapper queryWrapper) {
        return iPxstutableDao.getqiandanCountlist(queryWrapper);
    }


    @Override
    public Page<yixiangStuVo> getYixiangstuPages(Page page, QueryWrapper wrapper) {
        return iPxstutableDao.getYixiangstuPages(page, wrapper);
    }

    @Override
    public Page<genjinTixingVo> GetYixiangStuTixingPages(Page page, QueryWrapper wrapper) {
        return iPxstutableDao.GetYixiangStuTixingPages(page, wrapper);
    }

    @Override
    public List<PxStuTableVo> getExportYxStuList(QueryWrapper wrapper) {
        return iPxstutableDao.getExportYxStuList(wrapper);
    }
}
