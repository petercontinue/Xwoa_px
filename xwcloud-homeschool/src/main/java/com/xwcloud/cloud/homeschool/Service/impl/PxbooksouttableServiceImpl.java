package com.xwcloud.cloud.homeschool.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.homeschool.Dao.IPxbooksouttableDao;
import com.xwcloud.cloud.homeschool.Service.IPxbooksouttableService;

import com.xwcloud.cloud.model.Vo.PxbooksouttableVo;
import com.xwcloud.cloud.model.entity.Pxbooksouttable;
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
public class PxbooksouttableServiceImpl extends ServiceImpl<IPxbooksouttableDao, Pxbooksouttable> implements IPxbooksouttableService {

    @Override
    public Page<PxbooksouttableVo> getPage(Page page, Wrapper wrapper) {
        return this.baseMapper.getPage(page, wrapper);
    }

    @Override
    public List<PxbooksouttableVo> getJoinList(Wrapper wrapper) {
        return this.baseMapper.getJoinList(wrapper);
    }
}
