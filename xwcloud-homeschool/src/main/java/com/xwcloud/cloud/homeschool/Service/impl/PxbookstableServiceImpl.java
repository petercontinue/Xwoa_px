package com.xwcloud.cloud.homeschool.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.homeschool.Dao.IPxbookstableDao;
import com.xwcloud.cloud.homeschool.Service.IPxbookstableService;

import com.xwcloud.cloud.model.Vo.PxbookstableVo;
import com.xwcloud.cloud.model.entity.Pxbookstable;
import com.xwcloud.cloud.model.entity.Pxcampustable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-13
 */
@Service
public class PxbookstableServiceImpl extends ServiceImpl<IPxbookstableDao, Pxbookstable> implements IPxbookstableService {

    @Override
    public Page<PxbookstableVo> getPage(Page page, Wrapper wrapper) {
        return this.baseMapper.getPage(page, wrapper);
    }

    @Override
    public List<PxbookstableVo> getJoinList(Wrapper wrapper) {
        return this.baseMapper.getJoinList(wrapper);
    }

    @Override
    public List<Pxcampustable> getCampusList(Wrapper wrapper) {
        return this.baseMapper.getCampusList(wrapper);
    }
}
