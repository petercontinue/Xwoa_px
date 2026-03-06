package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.yuexiqokeVo;
import com.xwcloud.cloud.model.entity.Pxchongzhipaytable;
import com.xwcloud.cloud.pkxk.Dao.IPxchongzhipaytableDao;
import com.xwcloud.cloud.pkxk.Service.IPxchongzhipaytableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-20
 */
@Service
public class PxchongzhipaytableServiceImpl extends ServiceImpl<IPxchongzhipaytableDao, Pxchongzhipaytable> implements IPxchongzhipaytableService {
	@Autowired
    IPxchongzhipaytableDao iPxchongzhipaytableDao;

    @Override
    public Page<yuexiqokeVo> getyuexiaokePage(Page page, QueryWrapper queryWrapper) {
        return iPxchongzhipaytableDao.getyuexiaokePage(page, queryWrapper);
    }

    @Override
    public List<Pxchongzhipaytable> selectChongzhiPay(QueryWrapper queryWrapper) {
        return iPxchongzhipaytableDao.selectChongzhiPay(queryWrapper);
    }

    @Override
    public List<yuexiqokeVo> ExportyuexiaokePage(QueryWrapper queryWrapper) {
        return iPxchongzhipaytableDao.ExportyuexiaokePage(queryWrapper);
    }
}
