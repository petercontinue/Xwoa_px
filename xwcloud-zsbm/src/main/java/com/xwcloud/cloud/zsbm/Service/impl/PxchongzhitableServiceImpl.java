package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.chongzhiListVo;
import com.xwcloud.cloud.model.Vo.chongzhiliushuiVO;
import com.xwcloud.cloud.model.entity.Pxchongzhitable;

import com.xwcloud.cloud.zsbm.Dao.IPxchongzhitableDao;
import com.xwcloud.cloud.zsbm.Service.IPxchongzhitableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class PxchongzhitableServiceImpl extends ServiceImpl<IPxchongzhitableDao, Pxchongzhitable> implements IPxchongzhitableService {
    @Autowired
    IPxchongzhitableDao iPxchongzhitableDao;

    @Override
    public Page<chongzhiListVo> GetUserChongzhiListPages(Page page, QueryWrapper wrapper) {
        return iPxchongzhitableDao.GetUserChongzhiListPages(page, wrapper);
    }

    @Override
    public List<chongzhiListVo> GetUserChongzhiListList(QueryWrapper wrapper) {
        return iPxchongzhitableDao.GetUserChongzhiListList(wrapper);
    }

    @Override
    public Page<chongzhiliushuiVO> GetChongzhiliushuiPages(Page page, QueryWrapper wrapper) {
        return iPxchongzhitableDao.GetChongzhiliushuiPages(page, wrapper);
    }
}
