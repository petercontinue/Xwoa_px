package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.caiwu.Dao.IPxsalarystyletableDao;
import com.xwcloud.cloud.caiwu.Service.IPxsalarystyletableService;
import com.xwcloud.cloud.model.Vo.PxsalarystyletableVo;
import com.xwcloud.cloud.model.entity.Pxsalarystyletable;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-25
 */
@Service
public class PxsalarystyletableServiceImpl extends ServiceImpl<IPxsalarystyletableDao, Pxsalarystyletable> implements IPxsalarystyletableService {

    @Override
    public Page<PxsalarystyletableVo> getPage(Page page, Wrapper wrapper) {
        return this.baseMapper.getPage(page, wrapper);
    }
}
