package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.SumkehaoVo;
import com.xwcloud.cloud.model.Vo.stukehaoShowVo;
import com.xwcloud.cloud.model.entity.Pxkeshistutable;
import com.xwcloud.cloud.model.pkxk.Vo.stuKehaoVo;

import com.xwcloud.cloud.model.Vo.xiaokedayingVo;
import com.xwcloud.cloud.pkxk.Dao.IPxkeshistutableDao;
import com.xwcloud.cloud.pkxk.Service.IPxkeshistutableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-22
 */
@Service
public class PxkeshistutableServiceImpl extends ServiceImpl<IPxkeshistutableDao, Pxkeshistutable> implements IPxkeshistutableService {

    @Autowired
    IPxkeshistutableDao iPxkeshistutableDao;

    @Override
    public Page<stuKehaoVo> getStukehao(Page page, QueryWrapper queryWrapper) {
        return iPxkeshistutableDao.getStukehao(page, queryWrapper);
    }

    @Override
    public SumkehaoVo getkehao(Long buxiID, Long qiyeID) {
        return iPxkeshistutableDao.getkehao(buxiID, qiyeID);
    }

    @Override
    public List<stuKehaoVo> ExportStukehao(QueryWrapper queryWrapper) {
        return iPxkeshistutableDao.ExportStukehao(queryWrapper);
    }

    @Override
    public List<Pxkeshistutable> selectkehao(QueryWrapper queryWrapper) {
        return iPxkeshistutableDao.selectkehao(queryWrapper);
    }

    @Override
    public Page<stukehaoShowVo> getstukehaoShowPage(Page page, QueryWrapper queryWrapper) {
        return iPxkeshistutableDao.getstukehaoShowPage(page, queryWrapper);
    }

    @Override
    public Page<xiaokedayingVo> getxiaokedayingPage(Page page) {
        return iPxkeshistutableDao.getxiaokedayingPage(page);
    }
}
