package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.Pxstutable;
import com.xwcloud.cloud.pkxk.Dao.IPxstutableDao;
import com.xwcloud.cloud.pkxk.Service.IPxstutableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-05
 */
@Service
public class PxstutableServiceImpl extends ServiceImpl<IPxstutableDao, Pxstutable> implements IPxstutableService {
    @Autowired
    IPxstutableDao iPxstutableDao;

    @Override
    public Page<stuRemainVo> remainkeshishowPage(Page page, QueryWrapper queryWrapper) {
        return iPxstutableDao.remainkeshishowPage(page, queryWrapper);
    }

    @Override
    public List<ExportReMoneyVo> ExportReMoney(QueryWrapper queryWrapper) {
        return iPxstutableDao.ExportReMoney(queryWrapper);
    }

    @Override
    public Page<qiandanstuVo> getqiandanstuShowPage(Page page, QueryWrapper queryWrapper) {
        return iPxstutableDao.getqiandanstuShowPage(page, queryWrapper);
    }

    @Override
    public Page<ArtificialQiandaoVo> getPaikeQiandaoPage(Page page, QueryWrapper queryWrapper) {
        return iPxstutableDao.getPaikeQiandaoPage(page, queryWrapper);
    }

    @Override
    public List<Pxstutable> selectstu(QueryWrapper queryWrapper) {
        return iPxstutableDao.selectstu(queryWrapper);
    }

    @Override
    public List<getstuVo> getstu(QueryWrapper queryWrapper) {
        return iPxstutableDao.getstu(queryWrapper);
    }

    @Override
    public List<getclassVo> GetcampusStuName(Long qiyeID) {
        return iPxstutableDao.GetcampusStuName(qiyeID);
    }

    @Override
    public List<getclassVo> getxkStu(Long stuID, Long qiyeID) {
        return iPxstutableDao.getxkStu(stuID, qiyeID);
    }

    @Override
    public Page<HashMap<String, Object>> GetAllStuInfoAndMubanImages(Page page, QueryWrapper queryWrapper) {
        return iPxstutableDao.GetAllStuInfoAndMubanImages(page, queryWrapper);
    }
}
