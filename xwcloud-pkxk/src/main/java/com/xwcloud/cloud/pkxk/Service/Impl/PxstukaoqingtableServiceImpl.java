package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.KaoqingCountVo;
import com.xwcloud.cloud.model.Vo.KaoqingliushuiVo;
import com.xwcloud.cloud.model.Vo.nokaoqingStuVo;
import com.xwcloud.cloud.model.entity.Pxstukaoqingtable;

import com.xwcloud.cloud.pkxk.Dao.IPxstukaoqingtableDao;
import com.xwcloud.cloud.pkxk.Service.IPxstukaoqingtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
 */
@Service
public class PxstukaoqingtableServiceImpl extends ServiceImpl<IPxstukaoqingtableDao, Pxstukaoqingtable> implements IPxstukaoqingtableService {
	@Autowired
    IPxstukaoqingtableDao iPxstukaoqingtableDao;

    @Override
    public List<Pxstukaoqingtable> selectstukaoqing(QueryWrapper queryWrapper) {
        return iPxstukaoqingtableDao.selectstukaoqing(queryWrapper);
    }

    @Override
    public Page<KaoqingCountVo> getKaoqingCountPage(Page page, QueryWrapper queryWrapper) {
        return iPxstukaoqingtableDao.getKaoqingCountPage(page, queryWrapper);
    }

    @Override
    public List<KaoqingCountVo> ExportKaoqingCount(QueryWrapper queryWrapper) {
        return iPxstukaoqingtableDao.ExportKaoqingCount(queryWrapper);
    }

    @Override
    public Page<KaoqingliushuiVo> getKaoqingliushuiPage(Page page, QueryWrapper queryWrapper) {
        return iPxstukaoqingtableDao.getKaoqingliushuiPage(page, queryWrapper);
    }

    @Override
    public List<KaoqingliushuiVo> ExportKaoqingliushui(QueryWrapper queryWrapper) {
        return iPxstukaoqingtableDao.ExportKaoqingliushui(queryWrapper);
    }

    @Override
    public Page<nokaoqingStuVo> NokaoqingstuPage(Page page, QueryWrapper queryWrapper) {
        return iPxstukaoqingtableDao.NokaoqingstuPage(page,queryWrapper);
    }
}
