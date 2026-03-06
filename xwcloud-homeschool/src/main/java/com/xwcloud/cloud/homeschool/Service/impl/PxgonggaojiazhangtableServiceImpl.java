package com.xwcloud.cloud.homeschool.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.homeschool.Dao.IPxgonggaojiazhangtableDao;
import com.xwcloud.cloud.homeschool.Service.IPxgonggaojiazhangtableService;

import com.xwcloud.cloud.model.Vo.PxgonggaojiazhangtableVo;
import com.xwcloud.cloud.model.entity.Pxgonggaojiazhangtable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-12
 */
@Service
public class PxgonggaojiazhangtableServiceImpl extends ServiceImpl<IPxgonggaojiazhangtableDao, Pxgonggaojiazhangtable> implements IPxgonggaojiazhangtableService {

    @Override
    public Page<PxgonggaojiazhangtableVo> getPage(Page page, Wrapper wrapper) {
        return this.baseMapper.getPage(page, wrapper);
    }

    @Override
    public List<PxgonggaojiazhangtableVo> getJoinList(Wrapper wrapper) {
        return null;
    }
}
