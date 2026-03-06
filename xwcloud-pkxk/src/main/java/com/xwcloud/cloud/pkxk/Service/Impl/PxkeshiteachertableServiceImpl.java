package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.Vo.TeaKehaoVo;
import com.xwcloud.cloud.model.Vo.teakehaoCountVo;
import com.xwcloud.cloud.model.entity.Pxkeshiteachertable;
import com.xwcloud.cloud.pkxk.Dao.IPxkeshiteachertableDao;
import com.xwcloud.cloud.pkxk.Service.IPxkeshiteachertableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-04
 */
@Service
public class PxkeshiteachertableServiceImpl extends ServiceImpl<IPxkeshiteachertableDao, Pxkeshiteachertable> implements IPxkeshiteachertableService {
	@Autowired
    IPxkeshiteachertableDao iPxkeshiteachertableDao;

    @Override
    public List<Pxkeshiteachertable> selectTeakehao(QueryWrapper queryWrapper) {
        return iPxkeshiteachertableDao.selectTeakehao(queryWrapper);
    }

    @Override
    public Page<TeaKehaoVo> getTeakehaoPage(Page page, QueryWrapper queryWrapper) {
        return iPxkeshiteachertableDao.getTeakehaoPage(page, queryWrapper);
    }

    @Override
    public List<TeaKehaoVo> ExportTeakehao(QueryWrapper queryWrapper) {
        return iPxkeshiteachertableDao.ExportTeakehao(queryWrapper);
    }

    @Override
    public List<teakehaoCountVo> ExportTeakehaoCount(QueryWrapper queryWrapper) {
        return iPxkeshiteachertableDao.ExportTeakehaoCount(queryWrapper);
    }
}
